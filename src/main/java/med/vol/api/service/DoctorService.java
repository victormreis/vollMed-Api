package med.vol.api.service;

import jakarta.persistence.EntityNotFoundException;
import med.vol.api.dto.DoctorListDTO;
import med.vol.api.dto.DoctorUpdateDTO;
import med.vol.api.model.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Page<DoctorListDTO> getDoctorsPageable(Pageable page) {
//        Pageable sortedPage = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("name"));
//        The next block before the return is used to override the pageable and limit maximum request from frontend
//        to the maxPageSize
        int maxPageSize = 20;
        int safeSize = Math.min(page.getPageSize(), maxPageSize);
        Pageable safePageable = PageRequest.of(
                page.getPageNumber(),
                safeSize,
                page.getSort());
        return doctorRepository.findAllByActiveTrue(safePageable)
                .map(DoctorListDTO::new);

    }

    public Doctor updateDoctor(DoctorUpdateDTO doctor) {
        return doctorRepository.findById(doctor.id()).map(d -> {
            d.updateDoctor(doctor);
            return d;
        }).orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctor.id() + " Not Found"));
    }

    public void deleteDoctor(Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.deleteDoctor();

    }

    public Doctor getDoctorDetails(Long id) {
        return doctorRepository.getReferenceById(id);
    }
}
