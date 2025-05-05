package med.vol.api.validation.cancelAppointments;

import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.dto.CancelAppointmentDTO;

public interface CancelAppointmentValidator {

    void validate(CancelAppointmentDTO data);
}
