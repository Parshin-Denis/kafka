package com.example.orderservice.controller;

import com.example.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaController {

    @Value("${app.kafka.orderTopic}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public String sendEvent(@RequestBody OrderEvent orderEvent){
        kafkaTemplate.send(topicName, orderEvent);
        return "Event sent to Kafka";
    }

}
