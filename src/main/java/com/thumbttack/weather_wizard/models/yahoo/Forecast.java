package com.thumbttack.weather_wizard.models.yahoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Forecast {

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String day;
    @Getter
    @Setter
    private String high;
    @Getter
    @Setter
    private String low;
    @Getter
    @Setter
    private String text;
}