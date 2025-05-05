package med.vol.api.config.errorHandling;

public class AppointmentValidationEx extends RuntimeException {
    public AppointmentValidationEx(String message) {
        super(message);
    }
}
