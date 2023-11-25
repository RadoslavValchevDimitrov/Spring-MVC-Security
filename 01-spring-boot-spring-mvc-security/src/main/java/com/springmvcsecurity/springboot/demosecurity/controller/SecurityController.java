package com.springmvcsecurity.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }

    // add Request mapping for /leaders

    @GetMapping("/leaders")
    public String showManagers(){
        return"leaders";
    }
    @GetMapping("/systems")
    public String showAdmin(){

        return "admin";
    }
}
