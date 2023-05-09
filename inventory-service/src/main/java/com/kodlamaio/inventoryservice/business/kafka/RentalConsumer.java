package com.kodlamaio.inventoryservice.business.kafka;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final CarService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "rental-created",
            groupId = "car-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        log.info("Rental created event consumed {}", event);
        service.changeState(State.Rented, event.getCarId());
    }

    @KafkaListener(
            topics = "rental-deleted",
            groupId = "car-rental-delete"
    )
    public void consume(RentalDeletedEvent event) {
        log.info("Rental deleted event consumed {}", event);
        service.changeState(State.Available, event.getCarId());
    }
}
