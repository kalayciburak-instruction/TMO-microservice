package com.kodlamaio.paymentservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private UUID id;
    private String cardNumber;
    private String cardHoldeR;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    private String cardCvv;
    private double balance;
}

