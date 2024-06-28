package br.com.ordertech.order;
import org.springframework.cloud.stream.annotation.*;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaBindings {

    String INPUT = "input";

//    SubscribableChannel input();
}
