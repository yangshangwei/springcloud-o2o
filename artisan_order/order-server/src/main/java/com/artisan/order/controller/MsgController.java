package com.artisan.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MsgController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/sendMsg2ArtisanQueue")
    public void sendMsg(){
        log.info("begin to send msg to artisanQueue ....");
        this.amqpTemplate.convertAndSend(
                "artisanQueue",
                "[artisanQueue] I send one msg to u with RabbitMQ");
    }



    @GetMapping("/sendMsg2ArtisanQueue2")
    public void sendMsg2(){
        log.info("begin to send msg to artisanQueue2 ....");
        this.amqpTemplate.convertAndSend(
                "artisanQueue2",
                "[artisanQueue2] I send one msg to u with RabbitMQ");
    }


    @GetMapping("/sendMsg3ArtisanQueue3")
    public void sendMsg3(){
        log.info("begin to send msg to artisanQueue3 ....");
        this.amqpTemplate.convertAndSend(
                "artisanQueue3",
                "[artisanQueue3] I send one msg to u with RabbitMQ");
    }



    @GetMapping("/sendSMSMsg")
    public void sendSMSMsg(){
        log.info("begin to send msg to sendSMSMsg ....");
        this.amqpTemplate.convertAndSend(
                "smsExchange",
                "sms",
                "[SMSQueue] I send one msg to u with RabbitMQ");
    }


    @GetMapping("/sendEmailMsg")
    public void sendEmailMsg(){
        log.info("begin to send msg to sendEmailMsg ....");
        this.amqpTemplate.convertAndSend(
                "emailExchange",
                "email",
                "[EmailQueue] I send one msg to u with RabbitMQ");
    }
}
