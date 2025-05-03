package med.vol.api.validation;

import jakarta.validation.ValidationException;
import med.vol.api.dto.BookAppointmentsDTO;

import java.time.DayOfWeek;

public class WorkHoursValidation {

    public void validateWorkHours(BookAppointmentsDTO data) {
        var bookTime = data.date().getHour();

        var openHour = bookTime < 7;
        var closeHour = bookTime > 18;

        var sunday = data.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if(openHour || closeHour || sunday) {
            throw new ValidationException("Time selected must be between work hours: mon - sat 7AM - 7PM");
        }
    }
}
