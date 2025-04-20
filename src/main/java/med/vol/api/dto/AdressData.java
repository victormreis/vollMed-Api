package med.vol.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdressData(
        @NotBlank
        String adress,
        @NotBlank
        String postalCode,
        @NotBlank
        String city,
        @NotBlank
        String province) {
}
