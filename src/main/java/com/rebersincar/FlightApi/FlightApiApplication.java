package com.rebersincar.FlightApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class FlightApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightApiApplication.class, args);
    }
}
