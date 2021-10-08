package com.routee;

import com.routee.sms.Message;
import com.routee.sms.MessageService;
import com.routee.weather.WeatherClient;
import com.routee.weather.WeatherClientImpl;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

/**
 * CityWeatherPoller Tester.
 *
 */
public class CityWeatherPollerTest {

    @Test
    public void testSchedule() {
        final Message[] generatedMessages = new Message[1];

        WeatherClient weatherClient = new WeatherClientImpl(null) {
            @Override public JSONObject getCurrentWeather(String city) {
                return null;
            }

            @Override public Double getTemperature(String city) {
                return Double.valueOf(20);
            }
        };
        MessageService messageService = message -> {
            generatedMessages[0] = message;
            return null;
        };

        PollerConfig config = PollerConfig.builder()
                .city("Thessaloniki")
                .temperatureLimit(20.0)
                .maxExecutions(1)
                .delay(Duration.ofMillis(1000))
                .phoneNumber("+306922222222")
                .build();
        CityWeatherPoller poller = new CityWeatherPoller(config, weatherClient, messageService);
        poller.schedule();

        Message message = generatedMessages[0];
        Assert.assertNotNull(message);
        Assert.assertEquals("+306922222222", message.getPhone());
        Assert.assertEquals("Temperature is 20.0C.", message.getText());
    }


}