package med.vol.api.validation;

import med.vol.api.dto.BookAppointmentsDTO;

public interface BookAppointmentValidator {


    void validate(BookAppointmentsDTO data);
}
