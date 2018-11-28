package com.thumbttack.weather_wizard.repositories;

import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoDBRepository extends CrudRepository<CityInfoDB, String> {
}
