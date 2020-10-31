package com.tie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
