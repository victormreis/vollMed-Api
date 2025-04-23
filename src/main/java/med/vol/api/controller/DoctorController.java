package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.DoctorDetailsDTO;
import med.vol.api.dto.DoctorListDTO;
import med.vol.api.dto.DoctorUpdateDTO;
import med.vol.api.model.Doctor;
import med.vol.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @PostMapping
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody @Valid DoctorDTO doctorData, UriComponentsBuilder uriBuilder) {
       var doctor = new Doctor(doctorData);
        doctorService.registerDoctor(doctor);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
       return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));

    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> listDoctors(@PageableDefault(size = 10, sort = "name") Pageable page) {
        var pages = doctorService.getDoctorsPageable(page);

        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDTO> getDoctorDetails(@PathVariable Long id) {
        var doctor =  doctorService.getDoctorDetails(id);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateDTO doctorData) {
        var doctor = doctorService.updateDoctor(doctorData);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }

}
