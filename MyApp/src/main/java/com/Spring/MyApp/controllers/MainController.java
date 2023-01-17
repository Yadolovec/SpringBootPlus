package com.Spring.MyApp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${hellos}")
    private String s;
    @GetMapping("/main")
    public String hello(){
        System.out.println(s);
        return "hello";
    }
}
