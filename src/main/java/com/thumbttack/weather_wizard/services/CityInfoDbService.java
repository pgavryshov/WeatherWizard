package com.thumbttack.weather_wizard.services;

import com.thumbttack.weather_wizard.models.db.CityInfoDB;

import java.util.ArrayList;

public interface CityInfoDbService {

    ArrayList listAll();

    CityInfoDB getById(String name);

    CityInfoDB saveOrUpdate(CityInfoDB city);

    void delete(String name);

    boolean exist(String name);
}
