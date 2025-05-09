package med.vol.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.types.Reason;
import med.vol.api.types.Specialty;

import java.time.LocalDateTime;

public record CancelAppointmentDTO(
        @NotNull
        AppointmentsDetailsDTO appointment,
        @NotNull
        Reason reason

) {
}
