package com.example.RestGlassfishHelloWorld;

import com.looseboxes.ratelimiter.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    private final Logger log = LoggerFactory.getLogger(HelloResource.class);

    @Inject
    private RateLimiter<HttpServletRequest> rateLimiter;

    @GET
    @Produces("text/plain")
    public String hello() {
        log.info("RateLimiter: {}", rateLimiter);
        return "Hello, World!";
    }
}