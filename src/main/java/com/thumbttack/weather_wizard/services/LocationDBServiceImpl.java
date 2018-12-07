package com.thumbttack.weather_wizard.services;

import com.google.common.collect.Lists;
import com.thumbttack.weather_wizard.model.db.LocationDB;
import com.thumbttack.weather_wizard.repositories.LocationDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationDBServiceImpl implements LocationDbService {

    LocationDBRepository locationDBRepository;

    @Autowired
    public LocationDBServiceImpl(LocationDBRepository locationDBRepository) {
        this.locationDBRepository = locationDBRepository;
    }

    @Override
    public ArrayList listAll() {
        return Lists.newArrayList(locationDBRepository.findAll());
    }

    @Override
    public LocationDB getBy(Long id) {
        return locationDBRepository.findById(id).get();
    }

    @Override
    public LocationDB getByLocation(LocationDB locationDB) {
        return locationDBRepository.findByCityAndRegionAndCountry(locationDB.getCity(), locationDB.getRegion(), locationDB.getCountry());
    }

    @Override
    public LocationDB saveOrUpdate(LocationDB locationDB) {
        locationDBRepository.save(locationDB);
        return locationDB;
    }

    @Override
    public void delete(Long id) {
        locationDBRepository.deleteById(id);
    }

    @Override
    public boolean exist(Long id) {
        return locationDBRepository.existsById(id);
    }

    @Override
    public boolean exist(LocationDB locationDB) {
        return locationDBRepository.existsByCityAndRegionAndCountry(locationDB.getCity(), locationDB.getRegion(), locationDB.getCountry());
    }
}
