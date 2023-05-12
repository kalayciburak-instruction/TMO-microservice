package com.kodlamaio.commonpackage.utils.jwt.customer;

import com.kodlamaio.commonpackage.utils.constants.JwtClaims;
import com.kodlamaio.commonpackage.utils.dto.CustomerInfo;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class ParseJwtCustomerInfo {
    public static CustomerInfo getCustomerInformation(Jwt jwt) {
        var customer = new CustomerInfo();
        customer.setId(UUID.fromString(jwt.getClaim(JwtClaims.CUSTOMER_ID)));
        customer.setUsername(jwt.getClaim(JwtClaims.CUSTOMER_USERNAME));
        customer.setFirstName(jwt.getClaim(JwtClaims.CUSTOMER_FIRST_NAME));
        customer.setLastName(jwt.getClaim(JwtClaims.CUSTOMER_LAST_NAME));
        customer.setEmail(jwt.getClaim(JwtClaims.CUSTOMER_EMAIL));

        return customer;
    }
}
