package com.artisan.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收RabbitMQ消息的接收方
 */
@Slf4j
@Component
public class MessageReceive {


    /**
     *  queues指定对列名，需要先手工在RabbitMQ上建立队列artisanQueue
     * @param message
     */
//    @RabbitListener(queues = "artisanQueue")
//    public void processReceivedMsg(String message){
//        log.info("artisanQueue Received MSG : {}", message);
//    }



    /**
     *  queuesToDeclare自动创建队列
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("artisanQueue2"))
    public void processReceivedMsg2(String message){
        log.info("artisanQueue2 Received MSG : {}", message);
    }



    /**
     *  自动创建队列,Exchange和队列绑定
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("artisanQueue3"),
            exchange = @Exchange("artisanExchange3")
    ))
    public void processReceivedMsg3(String message){
        log.info("artisanQueue3 Received MSG : {}", message);
    }



    /**
     *  自动创建队列,Exchange和队列绑定,接收指定key的消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("SMSQueue"),
            exchange = @Exchange("smsExchange"),
            key = "sms"

    ))
    public void processSMSMsg(String message){
        log.info("SMSQueue Received MSG : {}", message);
    }

    /**
     *  自动创建队列,Exchange和队列绑定,接收指定key的消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("EmailQueue"),
            exchange = @Exchange("emailExchange"),
            key = "email"

    ))
    public void processEmailMsg(String message){
        log.info("EmailQueue Received MSG : {}", message);
    }

}
