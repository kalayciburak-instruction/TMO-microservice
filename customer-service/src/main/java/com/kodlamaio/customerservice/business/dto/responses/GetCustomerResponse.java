package com.kodlamaio.customerservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
