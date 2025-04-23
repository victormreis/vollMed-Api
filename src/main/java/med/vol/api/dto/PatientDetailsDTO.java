package med.vol.api.dto;

import med.vol.api.model.Adress;
import med.vol.api.model.Patient;
import med.vol.api.types.Specialty;

public record PatientDetailsDTO(Long id, String name, String phone, String healthNumber, Adress addrees) {

    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getHealthNumber(), patient.getAddrees());
    }
}
