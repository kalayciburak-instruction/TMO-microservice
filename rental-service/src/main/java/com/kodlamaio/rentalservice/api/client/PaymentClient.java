package com.kodlamaio.rentalservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping(value = "/api/payments/check")
    void proccessPayment(@RequestBody CreateRentalPaymentRequest request);
}
