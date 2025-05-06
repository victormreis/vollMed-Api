package med.vol.api.repository;

import med.vol.api.model.Doctor;
import med.vol.api.types.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable safePageable);

//@Query(value = """
//            SELECT *
//            FROM doctors
//            WHERE active = true
//            AND specialty = :specialty
//            AND id >= (SELECT FLOOR(RAND() * (SELECT MAX(id) FROM doctors WHERE active = true AND specialty = :specialty)))
//            LIMIT 1
//            """, nativeQuery = true)

@Query(value = """
        SELECT d.*
        FROM doctors d
        WHERE d.active = 1
          AND d.specialty = :specialty
          AND d.id NOT IN (
              SELECT a.doctor_id
              FROM appointments a
              WHERE a.appointment_date_time = :date
          )
        ORDER BY RAND()
        LIMIT 1;
        """, nativeQuery = true)
    Doctor getRandomDoctor(String specialty, LocalDateTime date);

    @Query(value = """
            SELECT active
            FROM doctors
            WHERE id = :id
            """, nativeQuery = true)
    int findActiveById(Long id);
}
