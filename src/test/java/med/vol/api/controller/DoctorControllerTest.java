package med.vol.api.controller;

import med.vol.api.dto.AddressData;
import med.vol.api.dto.DoctorDTO;
import med.vol.api.dto.DoctorDetailsDTO;
import med.vol.api.model.Doctor;
import med.vol.api.repository.DoctorRepository;
import med.vol.api.service.DoctorService;
import med.vol.api.types.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.Doc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorDTO> doctorDTOJacksonTester;

    @Autowired
    private JacksonTester<DoctorDetailsDTO> doctorDetailsDTOJacksonTester;

    @MockitoBean
    DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return status code 400 when json is invalid")
    @WithMockUser
    void shouldReturnStatusCode400WhenInvalidJson() throws Exception {
        var response = mvc.perform(post("/doctors"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return status code 200 when json is invalid")
    @WithMockUser
    void shouldReturnStatusCode200WhenJsonIsValid() throws Exception {
        var doctorDto = new DoctorDTO(
                "Doctor",
                "doctor@vollmed.ca",
                "2269779999",
                "ON3330098dX",
                Specialty.CARDIOLOGY,
                getAdress()
        );

        when(doctorRepository.save(any())).thenReturn(new Doctor(doctorDto));


        var response = mvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorDTOJacksonTester.write(doctorDto)
                                .getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private AddressData getAdress() {
        return new AddressData(
                "Oxoford St 1001",
                "N6H4K8",
                "London",
                "ON"
        );
    }
}