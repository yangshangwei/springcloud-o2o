package com.artisan.order.message;

import com.artisan.order.ArtisanOrderApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MessageSender extends ArtisanOrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMsg(){
        log.info("begin to send msg ....");
        this.amqpTemplate.convertAndSend("artisanQueue","I send one msg to u with RabbitMQ");
    }

}