package com.thumbttack.weather_wizard.converter;

import com.thumbttack.weather_wizard.model.domain.CityInfoDB;
import com.thumbttack.weather_wizard.model.domain.ConditionDB;
import com.thumbttack.weather_wizard.model.domain.ForecastDB;
import com.thumbttack.weather_wizard.model.domain.LocationDB;
import com.thumbttack.weather_wizard.model.yahoo.CityInfo;
import org.springframework.stereotype.Component;

import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class CityInfoToCityInfoDB {
    public CityInfoDB convert(CityInfo cityInfo, String name) {
        CityInfoDB cityInfoDB = new CityInfoDB();
        ForecastDB forecastDB = new ForecastDB(cityInfo.getResults().getChannel().getItem().getForecast());
        SortedSet<ForecastDB> set = new TreeSet<>();
        forecastDB.setCityInfoName(cityInfoDB);
        set.add(forecastDB);

        SortedSet<ConditionDB> setConditions = new TreeSet<>();
        setConditions.add(new ConditionDB(cityInfo.getResults().getChannel().getItem().getCondition()));
        cityInfoDB.setConditions(setConditions);
        cityInfoDB.setForecasts(set);
        cityInfoDB.setLocation(new LocationDB(cityInfo.getResults().getChannel().getLocation()));
        cityInfoDB.setName(name);
        return cityInfoDB;
    }
}
