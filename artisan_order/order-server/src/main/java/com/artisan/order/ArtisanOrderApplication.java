package com.artisan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
// 增加商品模块 对应的路径，否则不会扫描，无法调用
@EnableFeignClients(basePackages="com.artisan.product.client")
public class ArtisanOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtisanOrderApplication.class, args);
    }

}
