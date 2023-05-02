package com.kodlamaio.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UpdateModelRequest {
    @NotEmpty
    private UUID brandId;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
}
