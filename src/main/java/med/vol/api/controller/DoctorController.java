package med.vol.api.controller;

import med.vol.api.dto.DoctorDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


    @PostMapping
    public void registerDoctor(@RequestBody DoctorDTO doctorData) {
        System.out.println(doctorData.name());
    }


}
