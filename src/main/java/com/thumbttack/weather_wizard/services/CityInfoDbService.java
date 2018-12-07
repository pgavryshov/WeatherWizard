package com.thumbttack.weather_wizard.services;

import com.thumbttack.weather_wizard.model.db.CityInfoDB;

import java.util.ArrayList;

public interface CityInfoDbService {

    ArrayList listAll();

    CityInfoDB getById(String name);

    CityInfoDB saveOrUpdate(CityInfoDB city);

    void delete(String name);

    boolean exist(String name);
}
