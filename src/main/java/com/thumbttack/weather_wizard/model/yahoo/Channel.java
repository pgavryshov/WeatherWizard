package com.thumbttack.weather_wizard.model.yahoo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Getter
    @Setter
    private Units units;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String link;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    private String lastBuildDate;
    @Getter
    @Setter
    private String ttl;
    @Getter
    @Setter
    private Location location;
    @Getter
    @Setter
    private Wind wind;
    @Getter
    @Setter
    private Atmosphere atmosphere;
    @Getter
    @Setter
    private Astronomy astronomy;
    @Getter
    @Setter
    private Image image;
    @Getter
    @Setter
    private Item item;
}
