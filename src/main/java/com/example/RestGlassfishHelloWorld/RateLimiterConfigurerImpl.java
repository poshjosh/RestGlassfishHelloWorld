package com.example.RestGlassfishHelloWorld;

import com.looseboxes.ratelimiter.javaee.util.RateLimitConfig;
import com.looseboxes.ratelimiter.javaee.util.RateLimitConfigList;
import com.looseboxes.ratelimiter.javaee.util.RateLimitProperties;
import com.looseboxes.ratelimiter.javaee.web.RateLimiterConfigurer;
import com.looseboxes.ratelimiter.javaee.web.RequestToIdConverterRegistry;
import com.looseboxes.ratelimiter.rates.Rates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@javax.inject.Singleton
public class RateLimiterConfigurerImpl implements RateLimiterConfigurer {

    private final Logger log = LoggerFactory.getLogger(RateLimiterConfigurerImpl.class);

    private final String rateLimitConfigName = "rate-limit-config";

    @Inject
    public RateLimiterConfigurerImpl(RateLimitProperties properties) {
        properties.setApplicationPath(HelloApplication.APPLICATION_PATH);
        properties.setRateLimitConfigs(Collections.singletonMap(rateLimitConfigName, getRateLimitConfigList()));
        properties.setControllerPackages(Collections.singletonList(HelloResource.class.getPackage().getName()));
        properties.setDisabled(Boolean.FALSE);
        log.debug("Properties: {}", properties);
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

    @Override
    public void addConverters(RequestToIdConverterRegistry requestToIdConverterRegistry) {
        requestToIdConverterRegistry.registerDefaultConverter(rateLimitConfigName);
    }
}
