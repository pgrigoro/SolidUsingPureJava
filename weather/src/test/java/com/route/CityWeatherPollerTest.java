package com.route;

import com.route.sms.Message;
import com.route.sms.MessageService;
import com.route.weather.WeatherClient;
import com.route.weather.WeatherClientImpl;
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
                return Double.valueOf(18);
            }
        };
        MessageService messageService = message -> {
            generatedMessages[0] = message;
            return null;
        };

        PollerConfig config = PollerConfig.builder()
                .city("Thessaloniki")
                .temperatureLimit(20)
                .maxExecutions(1)
                .delay(Duration.ofMillis(1000))
                .phoneNumber("+306922222222")
                .build();
        CityWeatherPoller poller = new CityWeatherPoller(config, weatherClient, messageService);
        poller.schedule();

        Message message = generatedMessages[0];
        Assert.assertNotNull(message);
        Assert.assertEquals("+306922222222", message.getPhone());
        Assert.assertEquals("Your name and Temperature less than 20C. 18.0C", message.getText());
    }


}