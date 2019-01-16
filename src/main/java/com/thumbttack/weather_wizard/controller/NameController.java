package com.thumbttack.weather_wizard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.WeatherWizardApplication;
import com.thumbttack.weather_wizard.converter.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.exception.CustomException;
import com.thumbttack.weather_wizard.model.domain.CityInfoDB;
import com.thumbttack.weather_wizard.model.yahoo.CityInfo;
import com.thumbttack.weather_wizard.service.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import static com.thumbttack.weather_wizard.util.Utils.*;

@Controller
public class NameController {

    private CityInfoDbService cityInfoDbService;
    private CityInfoToCityInfoDB cityInfoToCityInfoDB;
    private JmsTemplate jmsTemplate;
    private Object modelAtrResult = null;

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
    public String saveCityName(@ModelAttribute("name") String name, ModelMap model) {
        CityInfoDB cityInfoDB;
        try {
            cityInfoDB = cityInfoToCityInfoDB.convert(queryToYahoo(name), name);
            jmsTemplate.convertAndSend(WeatherWizardApplication.CITY_INFO_QUEUE, cityInfoDB);
            modelAtrResult = true;
        } catch (Exception e) {
            modelAtrResult = null;
            model.put(MODEL_ATR_ERROR_MESSAGE, e.getMessage());
            throw new CustomException(e.getMessage(), model);
        } finally {
            model.addAttribute(MODEL_ATR_CITY_STATUS, modelAtrResult);
            return modelAtrResult != null ? PAGE_CITY_INFO_DB_SAVE : PAGE_ERROR;
        }
    }

    @RequestMapping(value = "getByName", method = RequestMethod.POST)
    public String getCityByName(@ModelAttribute("name") String name, ModelMap model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            modelAtrResult = mapper.writeValueAsString(cityInfoDbService.getById(name));
        } catch (NoSuchElementException e) {
            modelAtrResult = null;
            model.put(MODEL_ATR_ERROR_MESSAGE, e.getMessage());
            throw new CustomException(e.getMessage(), model);
        } finally {
            model.put(MODEL_ATR_CITY, modelAtrResult);
            return modelAtrResult != null ? PAGE_CITY_INFO_DB_GET : PAGE_ERROR;
        }
    }

    @RequestMapping(value = "removeByName", method = RequestMethod.POST)
    public String removeCityByName(@ModelAttribute("name") String name, ModelMap model) {
        try {
            cityInfoDbService.delete(name);
            modelAtrResult = true;
        } catch (EmptyResultDataAccessException e) {
            modelAtrResult = null;
            model.put(MODEL_ATR_ERROR_MESSAGE, e.getMessage());
            throw new CustomException(e.getMessage(), model);
        } finally {
            model.put(MODEL_ATR_CITY_STATUS, modelAtrResult);
            return modelAtrResult != null ? PAGE_CITY_INFO_DB_REMOVE : PAGE_ERROR;
        }
    }

    private CityInfo queryToYahoo(String name) throws URISyntaxException {
        return new RestTemplate().getForObject(new URI(createQuery(name)), CityInfo.class);
    }
}
