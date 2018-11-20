package com.thumbttack.weather_wizard.models.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoDB implements Serializable {
    @Getter
    @Setter
    @Id
    String name;
    @Getter
    @Setter
    @Embedded
    LocationDB location;
    @Getter
    @Setter
    @Embedded
    ConditionDB condition;
    @Getter
    @Setter
    @JoinColumn(name = "cityInfoName")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ForecastDB> forecasts = new HashSet<ForecastDB>();

}
