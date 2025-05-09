package med.vol.api.repository;

import jakarta.validation.constraints.NotNull;
import med.vol.api.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable safePageable);

    @Query("""
            SELECT p.active
            FROM patient p
            WHERE p.id = :id
            """)
    Boolean findByActiveId(Long id);
}
