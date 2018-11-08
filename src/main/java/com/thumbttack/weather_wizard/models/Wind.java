package com.thumbttack.weather_wizard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Wind {

    @Getter
    @Setter
    private String chill;
    @Getter
    @Setter
    private String direction;
    @Getter
    @Setter
    private String speed;


}