package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

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