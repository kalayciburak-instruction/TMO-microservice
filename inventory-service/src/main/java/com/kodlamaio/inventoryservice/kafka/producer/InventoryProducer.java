package com.kodlamaio.inventoryservice.kafka.producer;

import com.kodlamaio.commonpackage.events.Inventory.CarCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static Logger LOGGER = LoggerFactory.getLogger(InventoryProducer.class);

    public void sendMessage(CarCreatedEvent event) {
        LOGGER.info(String.format("Car created event => %s", event.toString()));

        Message<CarCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-created").build();

        kafkaTemplate.send(message);
    }
}
