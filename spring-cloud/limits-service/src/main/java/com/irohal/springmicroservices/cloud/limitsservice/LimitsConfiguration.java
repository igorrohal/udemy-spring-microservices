package com.irohal.springmicroservices.cloud.limitsservice;

public class LimitsConfiguration {

    private int minimum;
    private int maximum;

    protected LimitsConfiguration() {
    }

    public LimitsConfiguration(int minimum, int maximum) {
        this();
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }
}
