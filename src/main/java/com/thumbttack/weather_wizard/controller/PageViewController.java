package com.thumbttack.weather_wizard.controller;

import com.thumbttack.weather_wizard.service.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

import static com.thumbttack.weather_wizard.util.Utils.*;

@Controller
public class PageViewController {

    private CityInfoDbService cityInfoDbService;

    @Autowired
    public void setCityInfoDbService(CityInfoDbService cityInfoDbService) {
        this.cityInfoDbService = cityInfoDbService;
    }

    @GetMapping(URL_SAVE)
    public String saveCity() {
        return PAGE_CITY_INFO_DB_SAVE;
    }

    @GetMapping(URL_GET)
    public String getCity() {
        return PAGE_CITY_INFO_DB_GET;
    }

    @GetMapping(URL_GET_ALL)
    public String getAllCity(ModelMap modelMap) {
        ArrayList cityInfoDBS;
        try {
            cityInfoDBS = cityInfoDbService.listAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelMap.put(MODEL_ATR_CITY_NAME_ALL, cityInfoDBS);
        return PAGE_CITY_INFO_DB_GET_ALL;
    }

    @GetMapping(URL_REMOVE)
    public String removeCity() {
        return PAGE_CITY_INFO_DB_REMOVE;
    }

    @GetMapping(URL_HOME)
    public String home() {
        return PAGE_HOME;
    }

}
