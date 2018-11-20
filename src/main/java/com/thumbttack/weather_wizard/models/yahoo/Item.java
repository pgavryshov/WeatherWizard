package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String lat;
    @Getter
    @Setter
    private String _long;
    @Getter
    @Setter
    private String link;
    @Getter
    @Setter
    private String pubDate;
    @Getter
    @Setter
    private Condition condition;
    @Getter
    @Setter
    private Forecast forecast;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Guid guid;


}