package com.thumbttack.weather_wizard.models.db;

import com.thumbttack.weather_wizard.models.yahoo.Location;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
public class LocationDB extends Location {

    private LocationDB(String city, String country, String region) {
        this.location = new Location(city, country, region);
    }

    public LocationDB(Location location) {
        this(location.getCity(), location.getCountry(), location.getRegion());
    }

    @Embedded
    Location location;
}
