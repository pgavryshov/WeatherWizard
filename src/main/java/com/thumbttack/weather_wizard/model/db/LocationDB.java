package com.thumbttack.weather_wizard.model.db;

import com.thumbttack.weather_wizard.model.yahoo.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class LocationDB implements Serializable {

    public LocationDB(Location location) {
        this.city = location.getCountry();
        this.region = location.getRegion();
        this.country = location.getCountry();
    }

    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private String region;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long _id;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(city)
                .append(" ")
                .append(region)
                .append(" ")
                .append(country);
        return result.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (this == o) return true;
        if (!(o instanceof LocationDB)) return false;
        LocationDB locationDB = (LocationDB) o;
        return city.equals(locationDB.city) && country.equals(locationDB.country) && region.equals(locationDB.region);
    }
}
