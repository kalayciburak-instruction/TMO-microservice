package com.kodlamaio.inventoryservice.business.dto.requests.update;

import com.kodlamaio.inventoryservice.entities.enums.State;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    @NotEmpty
    private UUID modelId;
    @Min(2015)
    // TODO: NotFuture custom annotation
    private int modelYear;
    // TODO: add regex
    @NotEmpty
    private String plate;
    @NotEmpty
    private State state;
    @Min(value = 1)
    private double dailyPrice;
}
