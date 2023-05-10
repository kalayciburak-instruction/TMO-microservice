package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.CarClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = CarClientFallback.class)
public interface CarClient {
    @GetMapping(value = "/api/cars/check-car-available/{carId}")
    CarClientResponse checkIfCarAvailable(@PathVariable UUID carId);
}
