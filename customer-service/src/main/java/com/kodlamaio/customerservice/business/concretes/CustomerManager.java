package com.kodlamaio.customerservice.business.concretes;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.customerservice.business.abstracts.CustomerService;
import com.kodlamaio.customerservice.business.dto.requests.UpdateCustomerRequest;
import com.kodlamaio.customerservice.business.dto.responses.CreateCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetAllCustomersResponse;
import com.kodlamaio.customerservice.business.dto.responses.GetCustomerResponse;
import com.kodlamaio.customerservice.business.dto.responses.UpdateCustomerResponse;
import com.kodlamaio.customerservice.business.rules.CustomerBusinessRules;
import com.kodlamaio.customerservice.entities.Customer;
import com.kodlamaio.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapperService mapper;
    private final CustomerBusinessRules rules;

    @Override
    public List<GetAllCustomersResponse> getAll() {
        var customers = repository.findAll();
        var response = customers
                .stream()
                .map(customer -> mapper.forResponse().map(customer, GetAllCustomersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCustomerResponse getById(UUID id) {
        rules.checkIfCustomerNotExists(id);
        var customer = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(customer, GetCustomerResponse.class);

        return response;
    }

    @Override
    public CreateCustomerResponse add(CreateCustomerRequest request) {
        var customer = mapper.forRequest().map(request, Customer.class);
        repository.save(customer);
        var response = mapper.forResponse().map(customer, CreateCustomerResponse.class);

        return response;
    }

    @Override
    public UpdateCustomerResponse update(UUID id, UpdateCustomerRequest request) {
        rules.checkIfCustomerNotExists(id);
        var customer = mapper.forRequest().map(request, Customer.class);
        customer.setId(id);
        repository.save(customer);
        var response = mapper.forResponse().map(customer, UpdateCustomerResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCustomerNotExists(id);
        repository.deleteById(id);
    }
}
