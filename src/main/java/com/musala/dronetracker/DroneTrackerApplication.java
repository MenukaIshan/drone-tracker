package com.musala.dronetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroneTrackerApplication {

    private static final Logger logger = LoggerFactory.getLogger(DroneTrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DroneTrackerApplication.class, args);
    }

}
