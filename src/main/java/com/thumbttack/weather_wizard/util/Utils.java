package com.thumbttack.weather_wizard.util;

import org.yql4j.YqlQueryBuilder;

public interface Utils {

    String CONFIG_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    String CONFIG_RESOLVER_SUFFIX = ".jsp";
    String CONFIG_RESOLVER_CONTENT_TYPE = "text/html";
    int CONFIG_RESOLVER_ORDER = 1000;

    String PAGE_HOME = "/home";
    String PAGE_ERROR = "/error";
    String PAGE_CITY_INFO_DB_SAVE = "cityInfoDB/save";
    String PAGE_CITY_INFO_DB_GET = "cityInfoDB/getByName";
    String PAGE_CITY_INFO_DB_GET_ALL = "cityInfoDB/getAll";
    String PAGE_CITY_INFO_DB_REMOVE = "cityInfoDB/removeByName";

    String URL_HOME = "/";
    String URL_SAVE = "save";
    String URL_GET= "get";
    String URL_GET_ALL = "getAll";
    String URL_REMOVE = "remove";

    String MODEL_ATR_CITY_STATUS = "cityStatus";
    String MODEL_ATR_CITY= "city";
    String MODEL_ATR_CITY_NAME_ALL = "cityNameAll";
    String MODEL_ATR_ERROR_MESSAGE = "errorMessage";


    static String getYQLQuery() {
        return "select * from weather.forecast where woeid in (select woeid from geo.places(1) ";
    }

    static String createQuery(String sityName) {
        return YqlQueryBuilder
                .fromQueryString(getYQLQuery() + "where text=\"@name\")")
                .withVariable("name", sityName)
                .build()
                .toUri()
                .toString();
    }
}
