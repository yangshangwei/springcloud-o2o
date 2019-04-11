package com.artisan.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties：告诉springboot将本类中所有属性和配置文件中相关的配置进行绑定；
// prefix="customized"：指出将配置文件中customized下的所有属性进行一一映射
@ConfigurationProperties(prefix = "customized")
// 需要动态刷新配置，加上该注解
@RefreshScope
public class CustomizedConfig {

    private String apiUrl;
    private String apiCode;
}
