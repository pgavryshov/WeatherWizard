package com.thumbttack.weather_wizard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Astronomy {
    @Getter
    @Setter
    private String sunrise;
    @Getter
    @Setter
    private String sunset;
}
