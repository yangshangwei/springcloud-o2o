package com.artisan.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/order")
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getServerInfoFromClient")
    public String requestServer(){

        // 方式三  （使用@LoadBalanced注解）

        String msg = restTemplate.getForObject("http://ARTISAN-PRODUCT/product/serverMsg",String.class);

        log.info("msg from server : {}", msg);
        return msg;
    }




}
