package com.thumbttack.weather_wizard.services;

import com.google.common.collect.Lists;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.repositories.CityInfoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityInfoDbServiceImpl implements CityInfoDbService {

    private CityInfoDBRepository cityInfoDBRepository;

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
    public CityInfoDB saveOrUpdate(CityInfoDB city) {
        cityInfoDBRepository.save(city);
        return city;
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
