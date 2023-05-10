package com.kodlamaio.rentalservice.bvsiness.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.rentalservice.api.client.CarClient;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalBusinessrules {
    private final RentalRepository repository;
    private final CarClient carClient;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("RENTAL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        var response = carClient.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }
}
