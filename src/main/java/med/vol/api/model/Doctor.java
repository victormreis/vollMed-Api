package med.vol.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.AdressData;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.types.Specialty;

@Entity(name = "Doctor")
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private Double  mra;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Adress adress;

    public Doctor(DoctorDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.mra = data.mra();
        this.specialty = data.specialty();
        this.adress = new Adress(data.adress());
    }
}
