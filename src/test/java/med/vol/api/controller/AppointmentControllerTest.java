package med.vol.api.controller;

import med.vol.api.dto.AppointmentsDetailsDTO;
import med.vol.api.dto.BookAppointmentsDTO;
import med.vol.api.service.AppointmentService;
import med.vol.api.types.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<BookAppointmentsDTO> bookAppointmentsDTOJacksonJson;

    @Autowired
    private JacksonTester<AppointmentsDetailsDTO> appointmentsDetailsDTOJacksonJson;

    @MockitoBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return status code 400 when json is invalid")
    @WithMockUser
    void shouldReturnStatusCode400WhenDataIsInvalid() throws Exception {

        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Should return status code 200 when json is valid")
    @WithMockUser
    void shouldReturnStatusCode200WhenDataIsValid() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var appointmentDetails = new AppointmentsDetailsDTO(null, 21L, 21L, date);

        when(appointmentService.bookAppointment(any())).thenReturn(appointmentDetails);


        var response = mvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookAppointmentsDTOJacksonJson.write(
                                new BookAppointmentsDTO(21L, 21L,date, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var responseJson = appointmentsDetailsDTOJacksonJson.write(
                appointmentDetails
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(responseJson);

    }

}