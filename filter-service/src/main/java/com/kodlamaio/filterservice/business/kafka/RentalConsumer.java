package com.kodlamaio.filterservice.business.kafka;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "rental-created",
            groupId = "filter-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        log.info("Rental created event consumed {}", event);
        var filter = service.getByCarId(event.getCarId());
        filter.setState("Rented");
        service.save(filter);
    }

    @KafkaListener(
            topics = "rental-deleted",
            groupId = "filter-rental-delete"
    )
    public void consume(RentalDeletedEvent event) {
        log.info("Rental deleted event consumed {}", event);
        var filter = service.getByCarId(event.getCarId());
        filter.setState("Available");
        service.save(filter);
    }
}
