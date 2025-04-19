package med.vol.api.controller;

import med.vol.api.dto.DoctorDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


    @PostMapping
    public void registerDoctor(@RequestBody DoctorDTO doctor) {
        System.out.println(doctor);
    }


    @GetMapping
    public String test() {
        return "Test success!";
    }
}
