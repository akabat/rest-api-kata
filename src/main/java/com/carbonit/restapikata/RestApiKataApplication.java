package com.carbonit.restapikata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiKataApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApiKataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApiKataApplication.class, args);
        log.warn("Logger check...");
    }
}
