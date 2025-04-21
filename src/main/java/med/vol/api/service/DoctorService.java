package med.vol.api.service;

import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.DoctorListDTO;
import med.vol.api.model.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(DoctorDTO data) {
        doctorRepository.save(new Doctor(data));
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
        return doctorRepository.findAll(safePageable)
                .map(DoctorListDTO::new);

    }
}
