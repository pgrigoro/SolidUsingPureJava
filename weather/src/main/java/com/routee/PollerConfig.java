package com.routee;

import java.time.Duration;

import static java.util.Objects.requireNonNull;

public class PollerConfig {

    private final String city;
    private final Double temperatureLimit;
    private final Integer maxExecutions;
    private final Duration delay;
    private final String phoneNumber;

    private PollerConfig(PollerConfigBuilder builder) {
        this.city = builder.city;
        requireNonNull(this.city, "City cannot be null");

        this.temperatureLimit = builder.temperatureLimit;
        requireNonNull(this.city, "TemperatureLimit cannot be null");

        this.maxExecutions = builder.maxExecutions;
        requireNonNull(this.maxExecutions, "MaxExecutions cannot be null");

        this.delay = builder.delay;
        requireNonNull(this.delay, "Delay cannot be null");

        this.phoneNumber = builder.phoneNumber;
        requireNonNull(this.phoneNumber, "PhoneNumber cannot be null");
    }

    public String getCity() {
        return city;
    }

    public Double getTemperatureLimit() {
        return temperatureLimit;
    }

    public Integer getMaxExecutions() {
        return maxExecutions;
    }

    public Duration getDelay() {
        return delay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static PollerConfigBuilder builder() {
        return new PollerConfigBuilder();
    }

    public static final class PollerConfigBuilder {
        private String city;
        private Double temperatureLimit = 20.0;
        private String phoneNumber;
        private Integer maxExecutions = 10;
        private Duration delay = Duration.ofMinutes(1);

        public PollerConfigBuilder city(String city) {
            this.city = city;
            return this;
        }

        public PollerConfigBuilder temperatureLimit(Double temperatureLimit) {
            this.temperatureLimit = temperatureLimit;
            return this;
        }

        public PollerConfigBuilder maxExecutions(Integer maxExecutions) {
            this.maxExecutions = maxExecutions;
            return this;
        }

        public PollerConfigBuilder delay(Duration delay) {
            this.delay = delay;
            return this;
        }

        public PollerConfigBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PollerConfig build() {
            return new PollerConfig(this);
        }
    }
}
