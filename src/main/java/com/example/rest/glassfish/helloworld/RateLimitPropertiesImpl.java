package com.example.rest.glassfish.helloworld;

import com.looseboxes.ratelimiter.javaee.util.RateLimitConfig;
import com.looseboxes.ratelimiter.javaee.util.RateLimitConfigList;
import com.looseboxes.ratelimiter.javaee.util.RateLimitProperties;
import com.looseboxes.ratelimiter.rates.Rates;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@javax.inject.Singleton
public class RateLimitPropertiesImpl implements RateLimitProperties {

    public static final String DEFAULT_CONFIG_NAME = "default";

    private final String applicationPath;

    private final List<String> controllerPackages;

    private final Boolean disabled;

    private final Map<String, RateLimitConfigList> rateLimitConfigs;

    public RateLimitPropertiesImpl() {
        this.applicationPath = HelloApplication.APPLICATION_PATH;
        this.controllerPackages = Collections.singletonList(HelloResource.class.getPackage().getName());
        this.disabled = Boolean.FALSE;
        this.rateLimitConfigs = Collections.singletonMap(DEFAULT_CONFIG_NAME, getRateLimitConfigList());
    }

    private RateLimitConfigList getRateLimitConfigList() {
        RateLimitConfigList rateLimitConfigList = new RateLimitConfigList();
        rateLimitConfigList.setLimits(getRateLimits());
        rateLimitConfigList.setLogic(Rates.Logic.OR);
        return rateLimitConfigList;
    }

    private List<RateLimitConfig> getRateLimits() {
        RateLimitConfig config = new RateLimitConfig();
        config.setDuration(1);
        config.setLimit(2);
        config.setTimeUnit(TimeUnit.MINUTES);
        return Collections.singletonList(config);
    }

    @Override public String getApplicationPath() {
        return applicationPath;
    }

    @Override public List<String> getControllerPackages() {
        return controllerPackages;
    }

    @Override public Boolean getDisabled() {
        return disabled;
    }

    @Override public Map<String, RateLimitConfigList> getRateLimitConfigs() {
        return rateLimitConfigs;
    }

    @Override
    public String toString() {
        return "RateLimitPropertiesImpl{" +
                "applicationPath=" + applicationPath +
                ", controllerPackages=" + controllerPackages +
                ", disabled=" + disabled +
                ", rateLimitConfigs=" + rateLimitConfigs +
                '}';
    }
}
