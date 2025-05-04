package med.vol.api.validation;

import jakarta.validation.ValidationException;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueAppoitmentValidation implements BookAppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(BookAppointmentsDTO data){

        var startHour = data.dateAndTime().withHour(7);
        var endHour = data.dateAndTime().withHour(18);
        var patientHasAnotherAppointment =
                appointmentRepository.existsByPatientIdAndAppointmentDateTimeBetween(data.patientID(),
                startHour, endHour);

        if(patientHasAnotherAppointment) {
            throw new ValidationException("Patient cannot more than one appointment for the same day");
        }

    }
}
