package com.exemple.CellarApp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ManagerControler {

    private Logger LOGGER = LoggerFactory.getLogger(ManagerControler.class);

    @GetMapping
    public String greeting() {
        LOGGER.info("Get Greeting");
        return "Greetings from Spring Boot!";
    }

}