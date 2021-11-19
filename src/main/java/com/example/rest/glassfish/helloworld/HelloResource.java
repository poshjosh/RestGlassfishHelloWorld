package com.example.rest.glassfish.helloworld;

import com.looseboxes.ratelimiter.RateLimiter;
import com.looseboxes.ratelimiter.annotation.RateLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.concurrent.TimeUnit;

@Path("/hello-world")
public class HelloResource {

    private final Logger log = LoggerFactory.getLogger(HelloResource.class);

    @Inject
    private RateLimiter<HttpServletRequest> rateLimiter;

    @Context
    private HttpServletRequest request;

    @GET
    @Produces("text/plain")
    @RateLimit(limit = 2, duration = 1, timeUnit = TimeUnit.MINUTES)
    public String hello() {
        log.debug("Rate limiter = {}, request = {}", rateLimiter, request);
        return "Hello, World!";
    }
}