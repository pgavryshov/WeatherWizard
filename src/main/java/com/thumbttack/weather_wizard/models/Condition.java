package com.thumbttack.weather_wizard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Condition {

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String temp;
    @Getter
    @Setter
    private String text;


}