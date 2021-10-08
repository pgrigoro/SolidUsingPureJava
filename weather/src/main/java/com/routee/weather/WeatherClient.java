package com.routee.weather;

import org.json.JSONObject;

public interface WeatherClient {

    JSONObject getCurrentWeather(String city);

    Double getTemperature(String city);

}
