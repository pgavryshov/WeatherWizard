package com.thumbttack.weather_wizard.exeptions;

import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class IsForecastCopyException extends RuntimeException {
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String errorText;

    public IsForecastCopyException(String city, CityInfoDB cityInfoDB, Exception e) {
        this.city = city;
        this.date = cityInfoDB.getForecasts().first().getDate();
        this.errorText = e.getMessage();
    }
}
