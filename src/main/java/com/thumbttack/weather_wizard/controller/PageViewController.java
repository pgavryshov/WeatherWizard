package com.thumbttack.weather_wizard.controller;

import com.thumbttack.weather_wizard.services.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageViewController {

    private CityInfoDbService cityInfoDbService;

    @Autowired
    public void setCityInfoDbService(CityInfoDbService cityInfoDbService) {
        this.cityInfoDbService = cityInfoDbService;
    }

    @GetMapping("save")
    public String saveCity() {
        return "cityInfoDB/save";
    }

    @GetMapping("get")
    public String getCity() {
        return "cityInfoDB/getByName";
    }

    @GetMapping("getAll")
    public String getAllCity(ModelMap modelMap) {
        ArrayList cityInfoDBS;
        try {
            cityInfoDBS = cityInfoDbService.listAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelMap.put("getAll", cityInfoDBS);
        return "cityInfoDB/getAll";
    }

    @GetMapping("remove")
    public String removeCity() {
        return "cityInfoDB/removeByName";
    }

}
