package med.vol.api.validation.cancelAppointments;

import med.vol.api.config.errorHandling.AppointmentValidationEx;
import med.vol.api.dto.CancelAppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class TimeToCancelValidation implements CancelAppointmentValidator{
    @Override
    public void validate(CancelAppointmentDTO data) {

        System.out.println("Validou!!!!");

        var now = LocalDateTime.now();
        var bookTime = data.appointment().date();

        System.out.println("now " + now );
        System.out.println("book time " + bookTime );

        var difference = Duration.between(now, bookTime).toHours();

        System.out.println("Diferenca " + difference);
        if (difference < 24) {
            throw new AppointmentValidationEx("Appointments only can be canceled within at least 24 hours");
        }


    }
}
