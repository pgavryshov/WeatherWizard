package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {

    @Getter
    @Setter
    private String count;
    @Getter
    @Setter
    private String created;
    @Getter
    @Setter
    private String lang;
    @Getter
    @Setter
    private Results results;

}