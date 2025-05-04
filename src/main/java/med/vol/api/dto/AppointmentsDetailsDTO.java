package med.vol.api.dto;

import med.vol.api.model.Appointment;

import java.time.LocalDateTime;

public record AppointmentsDetailsDTO(
        Long id,
        Long DoctorID,
        Long PatientID,
        LocalDateTime date) {
    public AppointmentsDetailsDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(),
                appointment.getAppointmentDateTime());
    }
}
