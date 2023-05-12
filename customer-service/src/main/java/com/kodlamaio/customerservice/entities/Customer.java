package com.kodlamaio.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
