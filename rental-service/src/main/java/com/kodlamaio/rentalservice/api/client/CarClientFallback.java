package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallback implements CarClient {
    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        log.info("CAR SERVICE IS DOWN!");
        throw new RuntimeException("CAR SERVICE IS DOWN!");
    }
}
