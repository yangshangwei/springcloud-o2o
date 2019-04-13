package com.artisan.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ArtisanSink {

    // 同一个服务里面的通道名字不能一样，在不同的服务里可以相同名字的通道
    // 否则启动抛出如下异常  bean definition with this name already exists
    String INPUT = "MyMsgInput";

//    String OUTPUT = "MyMsgOutput";

    @Input(ArtisanSink.INPUT)
    SubscribableChannel input();

//    @Output(ArtisanSink.OUTPUT)
//    MessageChannel output();
}
