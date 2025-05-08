package med.vol.api.repository;

import med.vol.api.dto.AddressData;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.PatientDTO;
import med.vol.api.dto.PatientDetailsDTO;
import med.vol.api.model.Appointment;
import med.vol.api.model.Doctor;
import med.vol.api.model.Patient;
import med.vol.api.types.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Should return null when the only registered doctor is not available on the given date")
    void shouldReturnNullWhenOnlyDoctorIsUnavailableOnDate() {

        var nextMonday10Am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.ca", "1234556", Specialty.ORTHOPEDICS);
        var patient = registerPatient("Patient ", "ON0018996655");
        registerAppointment(doctor, patient, nextMonday10Am);


        var doctorAvailable = doctorRepository.getRandomDoctor(Specialty.ORTHOPEDICS.name(), nextMonday10Am);
        assertThat(doctorAvailable).isNull();
    }


    @Test
    @DisplayName("Should return a doctor when registered doctor is available on the given date")
    void shouldReturnDoctorIfDoctorIsRegisteredAndAvailable() {

        var nextMonday10Am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.ca", "1234556", Specialty.ORTHOPEDICS);


        var doctorAvailable = doctorRepository.getRandomDoctor(Specialty.ORTHOPEDICS.name(), nextMonday10Am);
        assertThat(doctorAvailable).isEqualTo(doctor);
    }





    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        testEntityManager.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String name, String email, String mra, Specialty specialty) {
        var doctor = new Doctor(doctorDetails(name, email, mra, specialty));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String healthNumber) {
        var patient = new Patient(patientDetails(name, healthNumber));
        testEntityManager.persist(patient);
        return patient;
    }

    private DoctorDTO doctorDetails(String name, String email, String mra, Specialty specialty) {
        return new DoctorDTO(
                name,
                email,
                "2269775566",
                mra,
                specialty,
                adressDetails()
        );
    }

    private PatientDTO patientDetails(String name, String healthNumber) {
        return new PatientDTO(
                name,
                "2269775566",
                healthNumber,
                adressDetails()
        );
    }

    private AddressData adressDetails() {
        return new AddressData(
                "Oxford St 1001",
                "N6H6J8",
                "London",
                "ON"
        );
    }
}