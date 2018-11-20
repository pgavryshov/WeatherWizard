package com.thumbttack.weather_wizard.services;

import com.thumbttack.weather_wizard.converters.CityInfoToCityInfoDB;
import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.repositories.CityInfoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityInfoDbServiceImpl implements CityInfoDbService {

    private CityInfoDBRepository cityInfoDBRepository;
    private CityInfoToCityInfoDB cityInfoToCityInfoDB;

    @Autowired
    public CityInfoDbServiceImpl(CityInfoDBRepository cityInfoDBRepository, CityInfoToCityInfoDB cityInfoToCityInfoDB) {
        this.cityInfoDBRepository = cityInfoDBRepository;
        this.cityInfoToCityInfoDB = cityInfoToCityInfoDB;
    }


    @Override
    public List<CityInfoDB> listAll() {
        return new ArrayList<>(cityInfoDBRepository.findAll());
    }

    @Override
    public CityInfoDB getById(Long id) {
        return null;
    }

    @Override
    public CityInfoDB saveOrUpdate(CityInfoDB city) {
        cityInfoDBRepository.save(city);
        return city;
    }

    @Override
    public void delete(Long id) {
        cityInfoDBRepository.deleteById(id);
    }

    @Override
    public CityInfoDB saveOrUpdateCity(CityInfo cityInfo) {
        CityInfoDB saveCity = saveOrUpdate(cityInfoToCityInfoDB.convert(cityInfo));
        System.out.println("Seved City id: " + saveCity.getName());
        return saveCity;
    }
}
