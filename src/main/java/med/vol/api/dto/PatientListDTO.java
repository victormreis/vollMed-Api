package med.vol.api.dto;

import med.vol.api.model.Patient;

public record PatientListDTO(String name, String healthNumber) {
    public PatientListDTO(Patient patient) {
        this(patient.getName(), patient.getHealthNumber());
    }

}
