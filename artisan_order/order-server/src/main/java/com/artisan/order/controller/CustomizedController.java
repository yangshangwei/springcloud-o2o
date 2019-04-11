package com.artisan.order.controller;

import com.artisan.order.config.CustomizedConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomizedController {

    @Autowired
    private CustomizedConfig customizedConfig;

    @GetMapping("/customizedPro")
    public String getCustomizedProperties(){
        String apiUrl = customizedConfig.getApiUrl();
        String apiCode = customizedConfig.getApiCode();
        log.info("apiUrl:{}, apiCode:{}",apiUrl,apiCode);
        return "apiUrl:" + apiUrl + " , apiCode:" + apiCode;
    }
}
