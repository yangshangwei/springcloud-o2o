package com.artisan.order.controller;

import com.artisan.order.dto.OrderDTO;
import com.artisan.order.message.ArtisanSink;
import com.artisan.order.message.ArtisanSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class MsgStreamController {

    @Autowired
    private ArtisanSink sink;


    @GetMapping("/sendMsgByStream")
    public void sendMsgByStream(){
        String message = "I am one msg sent by Spring Cloud Stream";
        sink.input().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendMsgByStream2")
    public void sendMsgByStream2(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("11111");
        orderDTO.setOrderAmount(new BigDecimal(9999));
        sink.input().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
