package med.vol.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
        @NotBlank
        String address,
        @NotBlank
        String postalCode,
        @NotBlank
        String city,
        @NotBlank
        String province) {
}
