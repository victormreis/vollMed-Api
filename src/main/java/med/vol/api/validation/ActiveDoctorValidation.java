package med.vol.api.validation;

import jakarta.validation.ValidationException;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidation implements BookAppointmentValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(BookAppointmentsDTO data) {

        if(data.doctorID() == null) {
            return;
        }

        var doctor = doctorRepository.findActiveById(data.doctorID());

        if(!doctor) {
            throw new ValidationException("Appointments cannot be booked with inactive doctors!");
        }
    }
}
