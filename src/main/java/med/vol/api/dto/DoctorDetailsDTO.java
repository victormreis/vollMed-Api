package med.vol.api.dto;

import jakarta.validation.Valid;
import med.vol.api.model.Adress;
import med.vol.api.model.Doctor;
import med.vol.api.types.Specialty;

public record DoctorDetailsDTO(Long id, String name, String email, String mra, Specialty specialty, Adress addrees) {


    public DoctorDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getMra(), doctor.getSpecialty(),
                doctor.getAddress());
    }
}
