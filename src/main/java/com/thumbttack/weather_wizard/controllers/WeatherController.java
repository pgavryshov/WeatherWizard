package com.thumbttack.weather_wizard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.Garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.exeptions.IsForecastCopyException;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.models.db.LocationDB;
import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import com.thumbttack.weather_wizard.services.CityInfoDbService;
import com.thumbttack.weather_wizard.services.LocationDbService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LocationDbService locationDbService;

    @Autowired
    public void setLocationDbService(LocationDbService locationDbService) {
        this.locationDbService = locationDbService;
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
        CityInfoDB cityInfoDB = cityInfoToCityInfoDB.convert(new RestTemplate()
                .getForObject(new URI(new YahooQueryBuilder().createQuery(name)), CityInfo.class), name);
        try {
            if (cityInfoDbService.exist(name)) {
                CityInfoDB oldCityInfoDB = cityInfoDbService.getById(name);
                oldCityInfoDB.update(cityInfoDB);
                cityInfoDB = oldCityInfoDB;
            } else {
                LocationDB locationDB = cityInfoDB.getLocation();
                if (locationDbService.exist(locationDB)) {
                    cityInfoDB.setLocation(locationDbService.getByLocation(locationDB));
                }
            }
            cityInfoDB = cityInfoDbService.saveOrUpdate(cityInfoDB);
        } catch (Exception e) {
            throw new IsForecastCopyException(name, cityInfoDB, e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cityInfoDB);
    }

    @GetMapping("getAll")
    public String getAllCity() throws JsonProcessingException {
        ArrayList cityInfoDBS = null;
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
        CityInfoDB cityInfoDB = null;
        try {
            cityInfoDB = cityInfoDbService.getById(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cityInfoDB);
    }

    @GetMapping("removeByName/{name}")
    public String removeCityByName(@PathVariable("name") String name) throws JsonProcessingException {
        try {
            cityInfoDbService.delete(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "done";
    }
}
