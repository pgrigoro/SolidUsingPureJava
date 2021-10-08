package com.routee.weather;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * WeatherClient Tester.
 *
 */
public class WeatherClientTest {

    private static final String API_KEY = "b385aa7d4e568152288b3c9f5c2458a5";

    private static WeatherClientImpl CLIENT;

    @BeforeClass
    public static void beforeClass(){
        CLIENT = new WeatherClientImpl(API_KEY);
    }

    @Test
    public void testMakeRequest() {
        Assert.assertNotNull(CLIENT.getTemperature("Thessaloniki"));
    }

    @Test(expected = WeatherNotFoundException.class)
    public void testMakeRequestInvalid() {
        CLIENT.getTemperature("no_valid_city");
    }

} 
