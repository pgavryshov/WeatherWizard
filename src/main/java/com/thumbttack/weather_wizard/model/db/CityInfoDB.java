package com.thumbttack.weather_wizard.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

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
    @JoinColumn(name = "cityInfoName")
    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    LocationDB location;
    @Getter
    @Setter
    @JoinColumn(name = "cityInfoName")
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @javax.persistence.OrderBy("date")
    private SortedSet<ConditionDB> conditions = new TreeSet<ConditionDB>();
    @Getter
    @Setter
    @JoinColumn(name = "cityInfoName")
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @javax.persistence.OrderBy("date")
    private SortedSet<ForecastDB> forecasts = new TreeSet<ForecastDB>();

    public void update(CityInfoDB cityInfoDB) {
        if (!cityInfoDB.getLocation().equals(location) && cityInfoDB.getLocation() != null)
            this.location = cityInfoDB.getLocation();
        forecasts.addAll(cityInfoDB.forecasts);
        conditions.addAll(cityInfoDB.conditions);
    }
}
