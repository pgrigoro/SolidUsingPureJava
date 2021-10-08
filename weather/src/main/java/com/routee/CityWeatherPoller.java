package com.route;

import com.route.sms.Message;
import com.route.sms.MessageService;
import com.route.sms.MessageServiceImpl;
import com.route.weather.WeatherClient;
import com.route.weather.WeatherClientImpl;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CityWeatherPoller implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(CityWeatherPoller.class.getName());

    private final PollerConfig config;
    private final WeatherClient weatherClient;
    private final MessageService messageService;
    private final CountDownLatch countDownLatch;

    public CityWeatherPoller(PollerConfig config, WeatherClient weatherClient, MessageService messageService) {
        this.config = config;
        Objects.requireNonNull(this.config, "PollerConfig cannot be null");
        this.countDownLatch = new CountDownLatch(config.getMaxExecutions());

        this.weatherClient = weatherClient;
        Objects.requireNonNull(this.weatherClient, "WeatherClient cannot be null");

        this.messageService = messageService;
        Objects.requireNonNull(this.messageService, "MessageService cannot be null");
    }

    public void schedule() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long milliseconds = this.config.getDelay().toMillis();
        ScheduledFuture poller = executor.scheduleWithFixedDelay(this, 0, milliseconds, MILLISECONDS);

        try {
            // Wait until the countDownLatch has counted down to zero
            countDownLatch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error while executing poller", e);
            poller.cancel(true);
            executor.shutdown();
        }
    }

    @Override
    public void run() {
        try {
            Double temp = weatherClient.getTemperature(this.config.getCity());
            String textMessage = getTemperatureMessage(temp);
            messageService.send(Message.builder()
                    .phone(config.getPhoneNumber())
                    .text(textMessage)
                    .build());
            LOGGER.info(format("Temperature Message: %s", textMessage));
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error while processing temperature", e);
        } finally {
            countDownLatch.countDown();
        }
    }

    private String getTemperatureMessage(Double temperature) {
        String textMessage;
        if(temperature.doubleValue() > config.getTemperatureLimit()) {
            textMessage = format("Your name and Temperature more than %sC. %sC", config.getTemperatureLimit(), temperature.doubleValue());
        } else {
            textMessage = format("Your name and Temperature less than %sC. %sC", config.getTemperatureLimit(), temperature.doubleValue());
        }

        return textMessage;
    }

    public static void main(String[] args) {
        PollerConfig config = PollerConfig.builder()
                .city("Thessaloniki")
                .temperatureLimit(20)
                .maxExecutions(10)
                .delay(Duration.ofMinutes(10))
                .phoneNumber("+306922222222")
                .build();
        WeatherClient weatherClient = new WeatherClientImpl("b385aa7d4e568152288b3c9f5c2458a5");
        MessageService messageService = new MessageServiceImpl("NWM1ZDVlMjhlNGIwYmFlNWY0YWNjZmVjOk1Ha05mcUd1ZDA=");

        CityWeatherPoller poller = new CityWeatherPoller(config, weatherClient, messageService);
        poller.schedule();
    }

}