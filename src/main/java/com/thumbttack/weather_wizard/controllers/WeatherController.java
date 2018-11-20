package com.thumbttack.weather_wizard.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thumbttack.weather_wizard.Garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import com.thumbttack.weather_wizard.services.CityInfoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@RestController
public class WeatherController {

    private CityInfoDbService cityInfoDbService;
    private CityInfoToCityInfoDB cityInfoToCityInfoDB;

    @Autowired
    public void setCityInfoDbService(CityInfoDbService cityInfoDbService) {
        this.cityInfoDbService = cityInfoDbService;
    }

    @Autowired
    public void setCityInfoToCityInfoDB(CityInfoToCityInfoDB cityInfoToCityInfoDB) {
        this.cityInfoToCityInfoDB = cityInfoToCityInfoDB;
    }

    @GetMapping("yahoo/cities/{name}")
    public String getCityForecast(@PathVariable("name") String name) throws URISyntaxException, IOException {
        URI obj = new URI(new YahooQueryBuilder().createQuery(name));
        String responce = new RestTemplate().getForObject(obj, String.class);
        CityInfo responce2 = new RestTemplate().getForObject(obj, CityInfo.class);

        JsonNode node = new XmlMapper().readTree(Objects.requireNonNull(responce).getBytes());

        CityInfoDB cityInfoDB = cityInfoToCityInfoDB.convert(responce2);
        cityInfoDB.setName(name);
        cityInfoDbService.saveOrUpdate(cityInfoDB);
        return new ObjectMapper().writeValueAsString(cityInfoDB.toString());
    }


}
