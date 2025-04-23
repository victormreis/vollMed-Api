package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.DoctorDetailsDTO;
import med.vol.api.dto.PatientDTO;
import med.vol.api.dto.PatientDetailsDTO;
import med.vol.api.model.Patient;
import med.vol.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;


    @PostMapping
    @Transactional
    public ResponseEntity registerPatient(@Valid @RequestBody PatientDTO patient, UriComponentsBuilder uriBuilder) {
        System.out.println(patient);
        var patientToRegister = new Patient(patient);
        patientService.register(patientToRegister);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patientToRegister.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patientToRegister));

    }
}
