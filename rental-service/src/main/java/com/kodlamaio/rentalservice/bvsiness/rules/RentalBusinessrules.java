package com.kodlamaio.rentalservice.bvsiness.rules;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.rentalservice.api.client.CarClient;
import com.kodlamaio.rentalservice.api.client.PaymentClient;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalBusinessrules {
    private final RentalRepository repository;
    private final CarClient carClient;
    private final PaymentClient paymentClient;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("RENTAL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        var response = carClient.checkIfCarAvailable(carId);
        checkClientResponse(response);
    }

    public void ensurePayment(CreateRentalPaymentRequest request) {
        var response = paymentClient.processPayment(request);
        checkClientResponse(response);
    }

    private void checkClientResponse(ClientResponse response) {
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }
}
