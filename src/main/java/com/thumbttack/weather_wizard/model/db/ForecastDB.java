package com.thumbttack.weather_wizard.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thumbttack.weather_wizard.model.yahoo.Forecast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDB implements Comparable<ForecastDB>, Serializable {

    public ForecastDB(Forecast forecast) {
        this.code = forecast.getCode();
        this.date = forecast.getDate();
        this.day = forecast.getDay();
        this.high = forecast.getHigh();
        this.low = forecast.getLow();
        this.text = forecast.getText();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long _id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityInfoName")
    @Setter
    @Getter
    @NonNull
    CityInfoDB cityInfoName;

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

    @Override
    public int compareTo(ForecastDB o) {
        return date.compareTo(o.date);
    }
}
