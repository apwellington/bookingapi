package com.challenge.bookingapi.resource;

import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;
import com.challenge.bookingapi.resource.dto.response.BookingDto;
import com.challenge.bookingapi.resource.dto.response.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class BookingResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }


    @Test
    public void whenFindAllBooking() throws Exception {
        mockMvc.perform(get("/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
    }
    @Test
    public void whenFindBookingByCustomer() throws Exception {
        mockMvc.perform(get("/booking/cuscode/{customer}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

    }

    @Test
    public void whenSaveBooking()  throws Exception {
        var bookingRequest = new BookingRequestDto();
        bookingRequest.setDescription("Booking Description Demo");
        bookingRequest.setDetail("Booking Detail Demo");
        bookingRequest.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-10"));
        bookingRequest.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-14"));
        bookingRequest.setRoomId(2L);
        bookingRequest.setCustomerId(2L);

        mockMvc.perform(post("/booking")
                        .content(mapToJson(bookingRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
    }

    @Test
    public void whenUpdateBooking()  throws Exception {
        var bookingRequest = new BookingRequestDto();
        bookingRequest.setDescription("Booking Description Demo");
        bookingRequest.setDetail("Booking Detail Demo Updated");
        bookingRequest.setFromDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-10"));
        bookingRequest.setToDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-14"));
        bookingRequest.setRoomId(2L);
        bookingRequest.setCustomerId(2L);

        mockMvc.perform(put("/booking/{bookingId}", 2)
                        .content(mapToJson(bookingRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
    }

    @Test
    public void whenTryingCancelBookingRunning()  throws Exception {
        var requestParams = new LinkedMultiValueMap<String, String>();
        requestParams.add("bookingID", "4");
        MvcResult result = mockMvc.perform(put("/booking")
                        .params(requestParams)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andReturn();
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
