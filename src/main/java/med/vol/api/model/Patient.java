package med.vol.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.PatientDTO;

@Entity(name = "patient")
@Table(name = "patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String phone;
    private String healthNumber;
    @Embedded
    private Adress addrees;


    public Patient(@Valid PatientDTO patient) {
        this.name = patient.name();
        this.phone = patient.phone();
        this.healthNumber = patient.healthNumber();
        this.addrees = new Adress(patient.addrees());
    }
}
