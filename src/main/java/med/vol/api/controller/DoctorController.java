package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;



    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid  DoctorDTO doctorData) {
        doctorService.registerDoctor(doctorData);
    }

}
