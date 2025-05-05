package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.PatientDTO;
import med.vol.api.dto.PatientDetailsDTO;
import med.vol.api.dto.PatientListDTO;
import med.vol.api.dto.PatientUpdateDTO;
import med.vol.api.model.Patient;
import med.vol.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;


    @PostMapping
    @Transactional
    public ResponseEntity registerPatient(@Valid @RequestBody PatientDTO patient, UriComponentsBuilder uriBuilder) {
        var patientToRegister = new Patient(patient);
        patientService.register(patientToRegister);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patientToRegister.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patientToRegister));

    }


    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> listPatients(@PageableDefault(size = 10, sort = "name") Pageable page) {
        var pages = patientService.getPatientsPageable(page);

        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatientById(@PathVariable Long id) {
        var patient = patientService.getPatientById(id);
        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatePatient(@RequestBody PatientUpdateDTO patient) {

        var patientUpdated = patientService.updatePatient(patient);


        return ResponseEntity.ok(new PatientDetailsDTO(patientUpdated));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);

        return ResponseEntity.noContent().build();
    }
}
