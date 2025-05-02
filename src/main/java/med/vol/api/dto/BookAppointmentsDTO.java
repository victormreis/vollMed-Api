package med.vol.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.types.Specialty;

import java.time.LocalDateTime;

public record BookAppointmentsDTO(
        Long doctorID,
        @NotNull
        Long patientID,
        @NotNull
        @Future
        LocalDateTime date,

        Specialty specialty
) {
}
