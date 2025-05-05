package med.vol.api.validation;

import lombok.Getter;
import med.vol.api.config.errorHandling.AppointmentValidationEx;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements BookAppointmentValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(BookAppointmentsDTO data) {
        var isPatientActive = patientRepository.findByActiveId(data.patientID());

        if (!isPatientActive) {
            throw new AppointmentValidationEx("Pattient must be active to book an appointment");
        }
    }
}
