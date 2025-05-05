package med.vol.api.validation;

import med.vol.api.config.errorHandling.AppointmentValidationEx;
import med.vol.api.dto.BookAppointmentsDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceScheduleValidation implements BookAppointmentValidator {

    public void validate(BookAppointmentsDTO data) {

        var bookTime = data.dateAndTime();
        var now = LocalDateTime.now();
        var difference = Duration.between(now, bookTime).toMinutes();
        if (difference < 30) {
            throw new AppointmentValidationEx("Appointments must be scheduled within at least 30 minutes");
        }

    }
}
