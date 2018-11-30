package com.thumbttack.weather_wizard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.WeatherWizardApplication;
import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import com.thumbttack.weather_wizard.services.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("yahoo")
public class WeatherController {

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

    @GetMapping("/save/{name}")
    public String saveCity(@PathVariable("name") String name) throws JsonProcessingException, URISyntaxException {
        CityInfoDB cityInfoDB = cityInfoToCityInfoDB.convert(queryToYahoo(name), name);
        jmsTemplate.convertAndSend(WeatherWizardApplication.CITY_INFO_QUEUE, cityInfoDB);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cityInfoDB);
    }

    @GetMapping("getAll")
    public String getAllCity() throws JsonProcessingException {
        ArrayList cityInfoDBS;
        try {
            cityInfoDBS = cityInfoDbService.listAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cityInfoDBS);
    }

    @GetMapping("getByName/{name}")
    public String getCityByName(@PathVariable("name") String name) throws JsonProcessingException {
        CityInfoDB cityInfoDB;
        try {
            cityInfoDB = cityInfoDbService.getById(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cityInfoDB);
    }

    @GetMapping("removeByName/{name}")
    public String removeCityByName(@PathVariable("name") String name) {
        try {
            cityInfoDbService.delete(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "done";
    }

    private CityInfo queryToYahoo(String name) throws URISyntaxException {
        return new RestTemplate().getForObject(new URI(new YahooQueryBuilder().createQuery(name)), CityInfo.class);
    }
}
