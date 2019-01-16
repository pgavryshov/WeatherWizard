package com.thumbttack.weather_wizard.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thumbttack.weather_wizard.WeatherWizardApplication;
import com.thumbttack.weather_wizard.exception.CustomException;
import com.thumbttack.weather_wizard.model.domain.CityInfoDB;
import com.thumbttack.weather_wizard.service.CityInfoDbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CityInfoListener {

    private CityInfoDbService cityInfoDbService;

    private static final Logger log = LogManager.getLogger(CityInfoListener.class);

    @Autowired
    public void setCityInfoDbService(CityInfoDbService cityInfoDbService) {
        this.cityInfoDbService = cityInfoDbService;
    }

    @JmsListener(destination = WeatherWizardApplication.CITY_INFO_QUEUE)
    public void receiveMessage(CityInfoDB cityInfoDB) throws JsonProcessingException {
        log.info("Received <" + cityInfoDB.getName() + ">");
        try {
            cityInfoDB = cityInfoDbService.saveOrUpdate(cityInfoDB);
        } catch (Exception e) {
            throw new CustomException();
        }
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(cityInfoDB));
    }
}
