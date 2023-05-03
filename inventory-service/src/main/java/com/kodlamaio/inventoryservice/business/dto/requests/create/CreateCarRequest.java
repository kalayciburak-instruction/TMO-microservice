package com.kodlamaio.inventoryservice.business.dto.requests.create;

import com.kodlamaio.commonpackage.utils.annotations.NotFutureYear;
import com.kodlamaio.commonpackage.utils.constants.Regex;
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
public class CreateCarRequest {
    @NotNull
    private UUID modelId;
    @Min(2015)
    @NotFutureYear
    private int modelYear;
    @NotBlank
    @Pattern(regexp = Regex.Plate, message = "Plate is not valid")
    private String plate;
    @Min(value = 1)
    private double dailyPrice;
}

