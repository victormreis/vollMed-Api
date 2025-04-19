package med.vol.api.dto;

public record DoctorDTO(String name,
                        String email,
                        String phone,
                        Double mra,
                        String specialization,
                        String adress) {
}
