package med.vol.api.service;

import med.vol.api.config.errorHandling.AppointmentValidationEx;
import med.vol.api.dto.AppointmentsDetailsDTO;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.dto.CancelAppointmentDTO;
import med.vol.api.model.Appointment;
import med.vol.api.model.Doctor;
import med.vol.api.repository.AppointmentRepository;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.repository.PatientRepository;
import med.vol.api.validation.BookAppointmentValidator;
import med.vol.api.validation.cancelAppointments.CancelAppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<BookAppointmentValidator> validators;

    @Autowired
    private List<CancelAppointmentValidator> validator;


        public AppointmentsDetailsDTO bookAppointment(BookAppointmentsDTO appointmentDetails) {
            if(!patientRepository.existsById(appointmentDetails.patientID())){
                throw new AppointmentValidationEx("Patient ID invalid");
            }

            if(appointmentDetails.doctorID() != null && !doctorRepository.existsById(appointmentDetails.doctorID())) {
                throw new AppointmentValidationEx("Doctor ID invalid");
            }

            validators.forEach(validator -> validator.validate(appointmentDetails));


            var patient = patientRepository.getReferenceById(appointmentDetails.patientID());
            var doctor = chooseDoctor(appointmentDetails);

            var appointment = new Appointment(null,doctor, patient, appointmentDetails.dateAndTime());


            appointmentRepository.save(appointment);

            return new AppointmentsDetailsDTO(appointment);
        }

    private Doctor chooseDoctor(BookAppointmentsDTO appointmentDetails) {
        if(appointmentDetails.doctorID() != null ) {
            return doctorRepository.getReferenceById(appointmentDetails.doctorID());
        }

        if(appointmentDetails.specialty() == null) {
            throw new jakarta.validation.ValidationException("Specialty is required when doctor is not selected!");
        }

        String specialty = appointmentDetails.specialty().name();

        return doctorRepository.getRandomDoctor(specialty, appointmentDetails.dateAndTime());
    }

    public void cancelAppointment(CancelAppointmentDTO data) {

        if (!appointmentRepository.existsById(data.appointment().id())) {
            throw new AppointmentValidationEx("Appointment ID Invalid");
        }

        validator.forEach(validator -> validator.validate(data));


        var appointment = appointmentRepository.getReferenceById(data.appointment().id());


        appointmentRepository.deleteById(appointment.getId());

    }
}
