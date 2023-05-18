package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service", fallback = CustomerClientFallback.class)
public interface CustomerClient {
    @Retry(name = "customer-retry")
    @PostMapping(value = "/api/customers")
    CreateCustomerRequest saveCustomer(@RequestBody CreateCustomerRequest request);
}
