package com.thumbttack.weather_wizard.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thumbttack.weather_wizard.Garbage.YahooQueryBuilder;
import com.thumbttack.weather_wizard.models.CityInfo;
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

    @GetMapping("yahoo/cities/{name}")
    public String getCityForecast(@PathVariable("name") String name) throws URISyntaxException, IOException {
        URI obj = new URI(new YahooQueryBuilder().createQuery(name));
        String responce = new RestTemplate().getForObject(obj, String.class);
        CityInfo responce2 = new RestTemplate().getForObject(obj, CityInfo.class);

        JsonNode node = new XmlMapper().readTree(Objects.requireNonNull(responce).getBytes());
        return new ObjectMapper().writeValueAsString(node);
    }

}
