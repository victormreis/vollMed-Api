package med.vol.api.service;

import med.vol.api.model.Patient;
import med.vol.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void register(Patient patientToRegister) {
        patientRepository.save(patientToRegister);
    }


}
