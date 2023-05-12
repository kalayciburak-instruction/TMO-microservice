package com.kodlamaio.customerservice.business.abstracts;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import com.kodlamaio.customerservice.business.dto.requests.UpdateCustomerRequest;
import com.kodlamaio.customerservice.business.dto.responses.CreateCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetAllCustomersResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.UpdateCustomerResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<GetAllCustomersResponse> getAll();
    GetCustomerResponse getById(UUID id);
    CreateCustomerResponse add(CreateCustomerRequest request);
    UpdateCustomerResponse update(UUID id, UpdateCustomerRequest request);
    void delete(UUID id);
}
