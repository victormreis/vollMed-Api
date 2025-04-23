package med.vol.api.service;

import med.vol.api.dto.PatientListDTO;
import med.vol.api.model.Patient;
import med.vol.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void register(Patient patientToRegister) {
        patientRepository.save(patientToRegister);
    }


    public Page<PatientListDTO> getPatientsPageable(Pageable page) {
        int maxPageSize = 20;
        int safeSize = Math.min(page.getPageSize(), maxPageSize);
        Pageable safePageable = PageRequest.of(page.getPageNumber(),safeSize, page.getSort());

        return patientRepository.findAll(safePageable).map(PatientListDTO::new);
    }
}
