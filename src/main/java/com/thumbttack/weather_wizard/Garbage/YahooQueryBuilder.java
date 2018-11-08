package com.thumbttack.weather_wizard.Garbage;

import org.yql4j.YqlQueryBuilder;

public class YahooQueryBuilder {

    public String createQuery(String sityName) {
        return YqlQueryBuilder
                .fromQueryString(Utils.getYQLQuery() + "where text=\"@name\")")
                .withVariable("name", sityName)
                .build()
                .toUri()
                .toString();
    }
}
