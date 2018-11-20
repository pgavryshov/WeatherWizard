package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private String region;


}