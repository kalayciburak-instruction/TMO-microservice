package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service")
public interface CustomerClient {
    @PostMapping(value = "/api/customers")
    CreateCustomerRequest saveCustomer(@RequestBody CreateCustomerRequest request);
}
