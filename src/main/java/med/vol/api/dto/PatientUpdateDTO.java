package med.vol.api.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.model.Adress;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        String name,
        Adress address) {
}
