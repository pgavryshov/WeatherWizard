package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Atmosphere {
    @Getter
    @Setter
    private String humidity;
    @Getter
    @Setter
    private String pressure;
    @Getter
    @Setter
    private String rising;
    @Getter
    @Setter
    private String visibility;
}