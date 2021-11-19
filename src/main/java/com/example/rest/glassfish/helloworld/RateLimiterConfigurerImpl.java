package com.example.rest.glassfish.helloworld;

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

    public RateLimiterConfigurerImpl() { }

    @Override
    public void addConverters(RequestToIdConverterRegistry requestToIdConverterRegistry) {
        requestToIdConverterRegistry.registerDefaultConverter(RateLimitPropertiesImpl.DEFAULT_CONFIG_NAME);
    }
}
