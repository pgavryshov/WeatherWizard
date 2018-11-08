package com.thumbttack.weather_wizard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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