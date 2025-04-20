package med.vol.api.dto;

import med.vol.api.model.Doctor;
import med.vol.api.types.Specialty;

public record DoctorListDTO(String name, String email, String mra, Specialty specialty) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getMra(), doctor.getSpecialty());
    }
}
