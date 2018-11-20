package com.thumbttack.weather_wizard.models.db;

import com.thumbttack.weather_wizard.models.yahoo.Forecast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDB {

    private ForecastDB(String code, String date, String text, String day, String high, String low) {
        this.forecast = new Forecast(code, date, day, high, low, text);
    }

    public ForecastDB(Forecast forecast) {
        this(forecast.getCode(), forecast.getDate(), forecast.getText(), forecast.getDay(), forecast.getHigh(), forecast.getLow());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long _id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityInfoName")
    @Setter
    @Getter
    @NonNull
    CityInfoDB cityInfoName;

    @Embedded
    @Getter
    Forecast forecast;

}
