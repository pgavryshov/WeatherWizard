package com.thumbttack.weather_wizard.services;

import com.google.common.collect.Lists;
import com.thumbttack.weather_wizard.model.db.CityInfoDB;
import com.thumbttack.weather_wizard.model.db.LocationDB;
import com.thumbttack.weather_wizard.repositories.CityInfoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CityInfoDbServiceImpl implements CityInfoDbService {

    private CityInfoDBRepository cityInfoDBRepository;
    private LocationDbService locationDbService;

    @Autowired
    public void setLocationDbService(LocationDbService locationDbService) {
        this.locationDbService = locationDbService;
    }

    @Autowired
    public CityInfoDbServiceImpl(CityInfoDBRepository cityInfoDBRepository) {
        this.cityInfoDBRepository = cityInfoDBRepository;
    }

    @Override
    public ArrayList listAll() {
        return Lists.newArrayList(cityInfoDBRepository.findAll());
    }

    @Override
    public CityInfoDB getById(String name) {
        return cityInfoDBRepository.findById(name).get();
    }

    @Override
    @Transactional
    public CityInfoDB saveOrUpdate(CityInfoDB cityInfoDB) {
        if (exist(cityInfoDB.getName())) {
            CityInfoDB oldCityInfoDB = getById(cityInfoDB.getName());
            oldCityInfoDB.update(cityInfoDB);
            cityInfoDB = oldCityInfoDB;
        } else {
            LocationDB locationDB = cityInfoDB.getLocation();
            if (locationDbService.exist(locationDB)) {
                cityInfoDB.setLocation(locationDbService.getByLocation(locationDB));
            }
        }
        cityInfoDBRepository.save(cityInfoDB);
        return cityInfoDB;
    }

    @Override
    public void delete(String name) {
        cityInfoDBRepository.deleteById(name);
    }

    @Override
    public boolean exist(String name) {
        return cityInfoDBRepository.existsById(name);
    }
}
