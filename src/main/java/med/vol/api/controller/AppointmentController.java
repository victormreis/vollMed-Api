package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.dto.CancelAppointmentDTO;
import med.vol.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity book(@RequestBody @Valid BookAppointmentsDTO appointmentDetails ) {
        var appointment = appointmentService.bookAppointment(appointmentDetails);
        return ResponseEntity.ok(appointment);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity deleteAppointment(@RequestBody @Valid CancelAppointmentDTO cancelAppointmentDTO) {


        appointmentService.cancelAppointment(cancelAppointmentDTO);

        return ResponseEntity.noContent().build();

    }
}
