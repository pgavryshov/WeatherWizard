package com.thumbttack.weather_wizard.repositories;

import com.thumbttack.weather_wizard.models.db.LocationDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDBRepository extends CrudRepository<LocationDB, Long> {
    LocationDB findByCityAndRegionAndCountry(String city, String region, String country);
    boolean existsByCityAndRegionAndCountry(String city, String region, String country);
}
