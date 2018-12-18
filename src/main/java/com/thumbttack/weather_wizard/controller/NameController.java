package com.thumbttack.weather_wizard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.WeatherWizardApplication;
import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.model.db.CityInfoDB;
import com.thumbttack.weather_wizard.model.yahoo.CityInfo;
import com.thumbttack.weather_wizard.services.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class NameController {

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

    @RequestMapping(value = "saveName", method = RequestMethod.POST)
    public String saveCityName(@ModelAttribute("name") String name,
                               ModelMap model) throws URISyntaxException, JsonProcessingException {
        System.out.println(name);
        CityInfoDB cityInfoDB = cityInfoToCityInfoDB.convert(queryToYahoo((String) name), (String) name);
        jmsTemplate.convertAndSend(WeatherWizardApplication.CITY_INFO_QUEUE, cityInfoDB);
        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("save", mapper.writeValueAsString(cityInfoDB));
        return "cityInfoDB/save";
    }

    @RequestMapping(value = "getByName", method = RequestMethod.POST)
    public String getCityByName(@ModelAttribute("name") String name, ModelMap model) throws JsonProcessingException {
        CityInfoDB cityInfoDB;
        try {
            cityInfoDB = cityInfoDbService.getById(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        model.put("getByName", mapper.writeValueAsString(cityInfoDB));
        return "cityInfoDB/getByName";
    }

    @RequestMapping(value = "removeByName", method = RequestMethod.POST)
    public String removeCityByName(@ModelAttribute("name") String name , ModelMap model) {
        try {
            cityInfoDbService.delete(name);
            model.put("removeCity", true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "cityInfoDB/removeByName";
    }

    private CityInfo queryToYahoo(String name) throws URISyntaxException {
        return new RestTemplate().getForObject(new URI(new YahooQueryBuilder().createQuery(name)), CityInfo.class);
    }
}
