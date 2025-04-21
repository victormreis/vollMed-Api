package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.DoctorListDTO;
import med.vol.api.dto.DoctorUpdateDTO;
import med.vol.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid DoctorDTO doctorData) {
        doctorService.registerDoctor(doctorData);
    }


    @GetMapping
    public Page<DoctorListDTO> listDoctors(@PageableDefault(size = 10, sort = "name") Pageable page) {
        return doctorService.getDoctorsPageable(page);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DoctorUpdateDTO doctorData) {
        doctorService.updateDoctor(doctorData);
    }

}
