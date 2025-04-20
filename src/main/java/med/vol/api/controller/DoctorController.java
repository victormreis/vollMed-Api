package med.vol.api.controller;

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
    public void registerDoctor(@RequestBody DoctorDTO doctorData) {
        doctorService.registerDoctor(doctorData);
    }


}
