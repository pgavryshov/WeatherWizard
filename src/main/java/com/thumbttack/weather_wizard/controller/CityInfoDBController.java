package com.thumbttack.weather_wizard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.model.db.CityInfoDB;
import com.thumbttack.weather_wizard.model.yahoo.CityInfo;
import com.thumbttack.weather_wizard.services.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Controller
public class CityInfoDBController {

    private CityInfoDbService cityInfoDbService;
    private CityInfoToCityInfoDB cityInfoToCityInfoDB;
    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    public void setCityInfoDbService(CityInfoDbService cityInfoDbService) {
        this.cityInfoDbService = cityInfoDbService;
    }

    @Autowired
    public void setCityInfoToCityInfoDB(CityInfoToCityInfoDB cityInfoToCityInfoDB) {
        this.cityInfoToCityInfoDB = cityInfoToCityInfoDB;
    }

    @GetMapping("/save")
    public String saveCity(Model model) throws JsonProcessingException, URISyntaxException {
//        if (name != null) {
//            CityInfoDB cityInfoDB = cityInfoToCityInfoDB.convert(queryToYahoo((String) name), (String) name);
//            jmsTemplate.convertAndSend(WeatherWizardApplication.CITY_INFO_QUEUE, cityInfoDB);
//            ObjectMapper mapper = new ObjectMapper();
//            model.addAttribute("save", mapper.writeValueAsString(cityInfoDB));
//        }

        if (!model.containsAttribute("name")) {
            model.addAttribute("name", null);
        }
        return "cityInfoDB/save";
    }

    @RequestMapping(value = "/saveName", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("name") String  name,
                             BindingResult result, ModelMap model) {
        System.out.println(name);
//        personService.savePerson(person);
        return "cityInfoDB/save";
    }

    @GetMapping("getAll")
    public String getAllCity(ModelMap modelMap) throws JsonProcessingException {
        ArrayList cityInfoDBS;
        try {
            cityInfoDBS = cityInfoDbService.listAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        modelMap.put("getAll", mapper.writeValueAsString(cityInfoDBS));
        return "cityInfoDB/getAll";
    }

    @GetMapping("getByName/{name}")
    public String getCityByName(@PathVariable("name") String name, ModelMap modelMap) throws JsonProcessingException {
        CityInfoDB cityInfoDB;
        try {
            cityInfoDB = cityInfoDbService.getById(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        modelMap.put("getByName", mapper.writeValueAsString(cityInfoDB));
        return "cityInfoDB/getByName";
    }

    @GetMapping("removeByName/{name}")
    public String removeCityByName(@PathVariable("name") String name) {
        try {
            cityInfoDbService.delete(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "cityInfoDB/removeByName";
    }

    private CityInfo queryToYahoo(String name) throws URISyntaxException {
        return new RestTemplate().getForObject(new URI(new YahooQueryBuilder().createQuery(name)), CityInfo.class);
    }
}
