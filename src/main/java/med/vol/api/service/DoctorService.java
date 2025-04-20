package med.vol.api.service;

import med.vol.api.dto.DoctorDTO;
import med.vol.api.model.Doctor;
import med.vol.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(DoctorDTO data) {
        doctorRepository.save(new Doctor(data));
    }

}
