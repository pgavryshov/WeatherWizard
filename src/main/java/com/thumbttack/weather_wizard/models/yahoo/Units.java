package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Units {

    @Getter
    @Setter
    private String distance;
    @Getter
    @Setter
    private String pressure;
    @Getter
    @Setter
    private String speed;
    @Getter
    @Setter
    private String temperature;



}