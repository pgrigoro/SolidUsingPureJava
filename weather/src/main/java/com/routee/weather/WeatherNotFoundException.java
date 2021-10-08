package com.route.weather;

public class WeatherNotFoundException extends RuntimeException {

    public WeatherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherNotFoundException(String message) {
        super(message);
    }

}
