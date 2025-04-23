package med.vol.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.PatientDTO;
import med.vol.api.dto.PatientDetailsDTO;
import med.vol.api.dto.PatientUpdateDTO;

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
    private Boolean active;


    public Patient(@Valid PatientDTO patient) {
        this.name = patient.name();
        this.phone = patient.phone();
        this.healthNumber = patient.healthNumber();
        this.addrees = new Adress(patient.addrees());
        this.active = true;
    }

    public void updatePatient(PatientUpdateDTO patientUpdate) {
        if(patientUpdate.name() != null) {
            this.name = patientUpdate.name();
        }

        if(patientUpdate.address() != null){
            this.addrees = patientUpdate.address();
        }
    }

    public void deletePatient() {
        this.active = false;
    }
}
