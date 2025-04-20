package med.vol.api.dto;

import med.vol.api.types.Specialty;

public record DoctorDTO(String name,
                        String email,
                        String phone,
                        Double mra,
                        Specialty specialty,
                        AdressData adress) {
}
