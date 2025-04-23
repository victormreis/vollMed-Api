package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.*;
import med.vol.api.model.Patient;
import med.vol.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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


    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> listPatients(@PageableDefault(size = 10, sort = "name") Pageable page) {
        var pages = patientService.getPatientsPageable(page);

        return ResponseEntity.ok(pages);
    }
}
