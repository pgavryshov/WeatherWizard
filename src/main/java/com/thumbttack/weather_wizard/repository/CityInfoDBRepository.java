package com.thumbttack.weather_wizard.repository;

import com.thumbttack.weather_wizard.model.domain.CityInfoDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoDBRepository extends CrudRepository<CityInfoDB, String> {
}
