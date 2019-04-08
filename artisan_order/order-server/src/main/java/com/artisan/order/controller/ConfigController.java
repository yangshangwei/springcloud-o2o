package com.artisan.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String url;

    @GetMapping("/value")
    public String getValueFromGit(){
        return url;
    }
}
