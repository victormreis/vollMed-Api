package med.vol.api.config.errorHandling;

public class VallidationException extends RuntimeException {
    public VallidationException(String message) {
        super(message);
    }
}
