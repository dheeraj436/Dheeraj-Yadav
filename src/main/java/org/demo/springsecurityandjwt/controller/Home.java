package org.demo.springsecurityandjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String dashboard() {
        String pod = System.getenv("HOSTNAME");
        System.out.println("Request served by: " + pod);
        return "Welcome to Home Page";
    }
}
