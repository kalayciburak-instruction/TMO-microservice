package com.kodlamaio.inventoryservice.business.dto.requests.update;

import com.kodlamaio.commonpackage.utils.annotations.NotFutureYear;
import com.kodlamaio.commonpackage.utils.constants.Regex;
import com.kodlamaio.inventoryservice.entities.enums.State;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull
    private UUID modelId;
    @Min(2015)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate, message = "Plate is not valid")
    @NotBlank
    private String plate;
    @NotNull
    private State state;
    @Min(value = 1)
    private double dailyPrice;
}
