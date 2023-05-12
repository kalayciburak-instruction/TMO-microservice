package com.kodlamaio.customerservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository repository;

    public void checkIfCustomerNotExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("CUSTOMER_NOT_EXISTS");
        }
    }
}
