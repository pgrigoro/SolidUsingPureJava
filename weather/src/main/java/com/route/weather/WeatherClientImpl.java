package com.route.weather;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

import static java.lang.String.format;

public class WeatherClientImpl implements WeatherClient {

    private static final String API_URI = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private final String apiKey;
    private final OkHttpClient client;

    public WeatherClientImpl(String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
    }

    public Double getTemperature(String city) {
        JSONObject currentWeather = getCurrentWeather(city);
        JSONObject mainWeather = JSONObject.class.cast(currentWeather.get("main"));
        return mainWeather.getDouble("temp");
    }

    public JSONObject getCurrentWeather(String city) {
        Request request = new Request.Builder()
                .url(format(API_URI, city, apiKey))
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                String error = format("Error while collecting weather info for city %s. %s", city, response.message());
                throw new WeatherNotFoundException(error);
            }

            return new JSONObject(response.body().string());
        } catch (IOException e) {
            throw new WeatherNotFoundException(format("Error while collecting weather info for city %s", city), e);
        }

    }

}