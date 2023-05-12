package com.kodlamaio.customerservice.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
