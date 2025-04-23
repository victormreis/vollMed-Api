package med.vol.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientDTO(
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @NotBlank
        String healthNumber,
        @NotNull
        AddressData addrees
) {
}
