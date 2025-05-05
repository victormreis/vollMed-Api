package med.vol.api.validation.cancelAppointments;

import med.vol.api.config.errorHandling.AppointmentValidationEx;
import med.vol.api.dto.CancelAppointmentDTO;
import org.springframework.stereotype.Component;

@Component
public class ReasonValidation implements CancelAppointmentValidator {


    @Override
    public void validate(CancelAppointmentDTO data) {

        System.out.println(data);

        if(data.reason() == null) {
            throw new AppointmentValidationEx("Reason must be informed to cancel an appoitment");
        }
    }
}
