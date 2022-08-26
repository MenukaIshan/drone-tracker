package com.musala.dronetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/hello/{user}")
    public String testMethod(@PathVariable String user){
        logger.info("Test method received {}", user);

        return "Hello, " + user;
    }
}
