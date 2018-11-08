package com.thumbttack.weather_wizard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String width;
    @Getter
    @Setter
    private String height;
    @Getter
    @Setter
    private String link;
    @Getter
    @Setter
    private String url;


}
