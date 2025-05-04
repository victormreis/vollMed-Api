package med.vol.api.validation;

import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConflictAppointmentValidation implements BookAppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(BookAppointmentsDTO data) {
        var isDoctorAvailableForThisTime = appointmentRepository.existsByDoctorIdAndAppointmentDateTime(data.doctorID(),
                data.dateAndTime());
    }
}
