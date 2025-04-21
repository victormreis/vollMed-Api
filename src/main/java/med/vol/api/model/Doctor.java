package med.vol.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.DoctorUpdateDTO;
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

    private String name;

    private String email;

    private String phone;

    private String  mra;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Adress address;

    public Doctor(DoctorDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.mra = data.mra();
        this.specialty = data.specialty();
        this.address = new Adress(data.address());
    }

    public void updateDoctor(DoctorUpdateDTO updatedData) {
        if(updatedData.name() != null) {
            this.name = updatedData.name();
        }
        if(updatedData.phone() != null) {
            this.phone = updatedData.phone();
        }
        if(updatedData.address() != null) {
            this.address.updateAddress(updatedData.address());
        }
    }
}
