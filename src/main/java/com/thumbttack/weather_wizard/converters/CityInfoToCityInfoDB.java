package com.thumbttack.weather_wizard.converters;

import com.thumbttack.weather_wizard.models.db.CityInfoDB;
import com.thumbttack.weather_wizard.models.db.ConditionDB;
import com.thumbttack.weather_wizard.models.db.ForecastDB;
import com.thumbttack.weather_wizard.models.db.LocationDB;
import com.thumbttack.weather_wizard.models.yahoo.CityInfo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CityInfoToCityInfoDB {
    public CityInfoDB convert(CityInfo cityInfo) {
        CityInfoDB cityInfoDB = new CityInfoDB();
        ForecastDB forecastDB = new ForecastDB(cityInfo.getResults().getChannel().getItem().getForecast());
        Set<ForecastDB> set = new HashSet<>();
        forecastDB.setCityInfoName(cityInfoDB);
        set.add(forecastDB);
        cityInfoDB.setCondition(new ConditionDB(cityInfo.getResults().getChannel().getItem().getCondition()));
        cityInfoDB.setForecasts(set);
        cityInfoDB.setLocation(new LocationDB(cityInfo.getResults().getChannel().getLocation()));
        return cityInfoDB;
    }
}
