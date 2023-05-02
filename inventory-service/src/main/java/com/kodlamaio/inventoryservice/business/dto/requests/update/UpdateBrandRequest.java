package com.kodlamaio.inventoryservice.business.dto.requests.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandRequest {
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
}
