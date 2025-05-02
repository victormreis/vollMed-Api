package med.vol.api.service;

import jakarta.validation.ValidationException;
import med.vol.api.config.errorHandling.VallidationException;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.model.Appointment;
import med.vol.api.model.Doctor;
import med.vol.api.repository.AppointmentRepository;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

        public void bookAppointment(BookAppointmentsDTO appointmentDetails) {
            if(!patientRepository.existsById(appointmentDetails.patientID())){
                throw new VallidationException("Patient ID invalid");
            }

            if(appointmentDetails.doctorID() != null && !doctorRepository.existsById(appointmentDetails.doctorID())) {
                throw new VallidationException("Doctor ID invalid");
            }


            var patient = patientRepository.getReferenceById(appointmentDetails.patientID());
            Doctor doctor = chooseDoctor(appointmentDetails);
            var appointment = new Appointment(null,doctor, patient, appointmentDetails.date());

//            appointmentRepository.save(appointment);
        }

    private Doctor chooseDoctor(BookAppointmentsDTO appointmentDetails) {
        if(appointmentDetails.doctorID() != null ) {
            return doctorRepository.getReferenceById(appointmentDetails.doctorID());
        }

        if(appointmentDetails.specialty() == null) {
            throw new ValidationException("Specialty is required when doctor is not selected!");
        }

        return doctorRepository.getRandomDoctor(appointmentDetails.specialty(), appointmentDetails.date());
    }
}
