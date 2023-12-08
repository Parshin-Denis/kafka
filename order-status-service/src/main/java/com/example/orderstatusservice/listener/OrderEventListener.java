package com.example.orderstatusservice.listener;

import com.example.events.OrderStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    @Value("${app.kafka.orderStatusTopic}")
    private String topicNameToSend;

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.orderTopic}",
    groupId = "${app.kafka.eventGroupId}",
    containerFactory = "orderEventConcurrentKafkaListenerContainerFactory")
    public void listen(){
        OrderStatusEvent orderStatusEvent = new OrderStatusEvent();
        orderStatusEvent.setStatus("Order is created");
        orderStatusEvent.setDate(Instant.now());
        kafkaTemplate.send(topicNameToSend, orderStatusEvent);
    }

}
