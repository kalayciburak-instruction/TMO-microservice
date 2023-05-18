package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientFallback implements CustomerClient {
    @Override
    public CreateCustomerRequest saveCustomer(CreateCustomerRequest request) {
        throw new BusinessException("CUSTOMER_SERVICE_IS_DOWN!");
    }
}
