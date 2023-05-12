package com.kodlamaio.customerservice.repository;

import com.kodlamaio.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
