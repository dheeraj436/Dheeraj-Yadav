package org.demo.springsecurityandjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String test() {
        return "Welcome to Home Page";
    }
}
