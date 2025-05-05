package med.vol.api.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.ValidationException;
import med.vol.api.config.errorHandling.AppointmentValidationEx;

public enum Reason {


    PATIENT_GAVE_UP("Patient gave up"),
    DOCTOR_CANCELED("Doctor canceled"),
    OTHER("Other");

    private final String description;


    Reason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Reason fromDescription(String description) {
        for (Reason reason : Reason.values()) {
            if (reason.getDescription().equalsIgnoreCase(description)) {
                return reason;
            }
        }
        throw new AppointmentValidationEx("Unknown description: " + description);
    }

    }