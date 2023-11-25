package com.springmvcsecurity.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {


    @GetMapping("/showLoginPage")
    public String showLogInForm(){
        return  "fancy-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }

}
