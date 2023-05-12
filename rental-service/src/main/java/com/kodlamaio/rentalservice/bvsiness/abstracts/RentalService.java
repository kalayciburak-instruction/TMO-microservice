package com.kodlamaio.rentalservice.bvsiness.abstracts;

import com.kodlamaio.rentalservice.bvsiness.dto.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(UUID id);
    CreateRentalResponse add(CreateRentalRequest request, UUID customerId);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);
    void delete(UUID id);
}
