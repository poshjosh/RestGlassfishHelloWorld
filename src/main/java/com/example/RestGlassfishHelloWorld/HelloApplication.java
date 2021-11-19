package com.example.RestGlassfishHelloWorld;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.time.LocalDateTime;

@ApplicationPath(HelloApplication.APPLICATION_PATH)
public class HelloApplication extends Application {
    public static final String APPLICATION_PATH = "/api";
}