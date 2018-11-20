package com.thumbttack.weather_wizard.repositories;

import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoDBRepository extends JpaRepository<CityInfoDB, Long> {
}
