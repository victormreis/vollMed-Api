package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.AppointmentsDetailsDTO;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity book(@RequestBody @Valid BookAppointmentsDTO appointmentDetails ) {
        appointmentService.bookAppointment(appointmentDetails);
        return ResponseEntity.ok(new AppointmentsDetailsDTO(null, null, null, null));
    }
}
