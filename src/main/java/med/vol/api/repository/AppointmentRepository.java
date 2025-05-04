package med.vol.api.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndAppointmentDateTime(Long doctorId, LocalDateTime appointmentDateTime);


    Boolean existsByPatientIdAndAppointmentDateTimeBetween(Long patientId, LocalDateTime star, LocalDateTime end);
}
