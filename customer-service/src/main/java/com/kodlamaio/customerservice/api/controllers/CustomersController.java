package com.kodlamaio.customerservice.api.controllers;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import com.kodlamaio.customerservice.business.abstracts.CustomerService;
import com.kodlamaio.customerservice.business.dto.requests.UpdateCustomerRequest;
import com.kodlamaio.customerservice.business.dto.responses.CreateCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetAllCustomersResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.UpdateCustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomersController {
    private final CustomerService service;

    @GetMapping
    public List<GetAllCustomersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCustomerResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public CreateCustomerResponse add(@Valid @RequestBody CreateCustomerRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCustomerResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCustomerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}