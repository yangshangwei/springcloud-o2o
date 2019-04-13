package com.artisan.order.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 接收方
 */

@Component
// Step1 注解  绑定刚才的接口
@EnableBinding(ArtisanSink.class)
@Slf4j
public class StreamReceive {


    // Step2  @StreamListener 绑定对象的名称
    @StreamListener(ArtisanSink.INPUT)
    @SendTo(ArtisanSource.OUTPUT)
    public String processStreamMsg(Object msg){
        log.info("INPUT StreamReceive: {}",msg);
        return "received OK ";
    }




}

