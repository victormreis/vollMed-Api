package med.vol.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookAppointmentsDTO(
        Long doctorID,
        @NotNull
        Long PatientID,
        @NotNull
        @Future
        LocalDateTime date
) {
}
