package com.kodlamaio.filterservice.kafka;

import com.kodlamaio.commonpackage.events.Inventory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.Inventory.CarDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private static Logger LOGGER = LoggerFactory.getLogger(InventoryConsumer.class);

    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "car-created",
            groupId = "car-create"
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapper.forRequest().map(event, Filter.class);
        service.save(filter);
        LOGGER.info("Car created event consumed {}", event);
    }

    @KafkaListener(
            topics = "car-deleted",
            groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event) {
        service.deleteByCarId(event.getCarId());
        LOGGER.info("Car deleted event consumed {}", event);
    }

}
