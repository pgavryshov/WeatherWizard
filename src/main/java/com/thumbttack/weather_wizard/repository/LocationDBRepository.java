package com.thumbttack.weather_wizard.repository;

import com.thumbttack.weather_wizard.model.domain.LocationDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDBRepository extends CrudRepository<LocationDB, Long> {
    LocationDB findByCityAndRegionAndCountry(String city, String region, String country);
    boolean existsByCityAndRegionAndCountry(String city, String region, String country);
}
