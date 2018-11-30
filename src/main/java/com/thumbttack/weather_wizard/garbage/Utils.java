package com.thumbttack.weather_wizard.garbage;

public class Utils {

    public static String getYQLQuery(){
        return "select * from weather.forecast where woeid in (select woeid from geo.places(1) ";
    }
}
