package com.example.rest.glassfish.helloworld;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(HelloApplication.APPLICATION_PATH)
public class HelloApplication extends Application {
    public static final String APPLICATION_PATH = "/api";
}