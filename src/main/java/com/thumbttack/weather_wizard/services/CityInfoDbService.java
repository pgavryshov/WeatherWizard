package com.thumbttack.weather_wizard.services;

import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;

import java.util.List;

public interface CityInfoDbService {

    List<CityInfoDB> listAll();

    CityInfoDB getById(Long id);

    CityInfoDB saveOrUpdate(CityInfoDB city);

    void delete(Long id);

    CityInfoDB saveOrUpdateCity(CityInfo cityInfo);
}
