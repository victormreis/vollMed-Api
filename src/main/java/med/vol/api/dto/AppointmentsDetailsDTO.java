package med.vol.api.dto;

import java.time.LocalDateTime;

public record AppointmentsDetailsDTO(
        Long id,
        Long DoctorID,
        Long PatientID,
        LocalDateTime date) {
}
