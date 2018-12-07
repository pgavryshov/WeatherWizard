package com.thumbttack.weather_wizard.services;

import com.thumbttack.weather_wizard.model.db.LocationDB;

import java.util.ArrayList;

public interface LocationDbService {
    ArrayList listAll();

    LocationDB getBy(Long name);

    LocationDB getByLocation(LocationDB locationDB);

    LocationDB saveOrUpdate(LocationDB city);

    void delete(Long name);

    boolean exist(Long name);

    boolean exist(LocationDB locationDB);
}
