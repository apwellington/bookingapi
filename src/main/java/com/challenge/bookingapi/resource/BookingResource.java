package com.challenge.bookingapi.resource;

import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.resource.dto.BookingDto;
import com.challenge.bookingapi.resource.dto.ResponseDto;
import com.challenge.bookingapi.service.IBookingService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.challenge.bookingapi.util.GenericMapper.mapCollection;


@RestController
@RequestMapping("/booking")
@Log4j2
public class BookingResource {

    private final IBookingService bookingService;
    private final ModelMapper modelMapper;


    public BookingResource(IBookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findAllBooking(){
        log.info(String.format("findAllBooking() - init at %s", LocalDate.now()));
        var response = new ResponseDto<Collection<BookingDto>>();
        response.setDate(LocalDateTime.now());
        try{
            this.bookingService.findAllBooking().ifPresent(item -> {
                response.setData(mapCollection(item, BookingDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
        }catch (BookingException be){
            log.error(String.format("Error getting all booking. Cause: %s", be.getMessage()));
            response.setMessage(be.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.internalServerError().body(response);
    }

}
