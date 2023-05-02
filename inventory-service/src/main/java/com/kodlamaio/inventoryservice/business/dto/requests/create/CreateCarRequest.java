package com.kodlamaio.inventoryservice.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    @NotNull
    private UUID modelId;
    @Min(2015)
    // TODO: NotFuture custom annotation
    private int modelYear;
    @NotEmpty
    // TODO: add regex
    private String plate;
    @Min(value = 1)
    private double dailyPrice;
}

