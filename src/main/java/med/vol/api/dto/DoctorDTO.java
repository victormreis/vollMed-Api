package med.vol.api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vol.api.types.Specialty;

public record DoctorDTO(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String mra,
        @NotNull
        Specialty specialty,
        @NotNull
        AddressData address) {
}
