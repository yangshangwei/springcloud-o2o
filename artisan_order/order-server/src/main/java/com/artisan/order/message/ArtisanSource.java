package com.artisan.order.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ArtisanSource {

    String OUTPUT = "MyMsgOutput";

    @Output(ArtisanSource.OUTPUT)
    MessageChannel output();
}
