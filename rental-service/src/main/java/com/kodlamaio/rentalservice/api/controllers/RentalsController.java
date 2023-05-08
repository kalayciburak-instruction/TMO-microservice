package com.kodlamaio.rentalservice.api.controllers;

import com.kodlamaio.rentalservice.bvsiness.abstracts.RentalService;
import com.kodlamaio.rentalservice.bvsiness.dto.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.UpdateRentalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService service;

    @GetMapping
    public List<GetAllRentalsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getByID(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse add(@PathVariable UUID id, @Valid @RequestBody UpdateRentalRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
