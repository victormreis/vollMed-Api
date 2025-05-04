package med.vol.api.validation;

import jakarta.validation.ValidationException;
import med.vol.api.dto.BookAppointmentsDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkHoursValidation implements BookAppointmentValidator {

    public void validate(BookAppointmentsDTO data) {
        var bookTime = data.dateAndTime().getHour();

        var openHour = bookTime < 7;
        var closeHour = bookTime > 18;

        var sunday = data.dateAndTime().getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if(openHour || closeHour || sunday) {
            throw new ValidationException("Time selected must be between work hours: mon - sat 7AM - 7PM");
        }
    }
}
