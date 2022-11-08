package com.challenge.bookingapi.resource;

import com.challenge.bookingapi.exception.BookingAppException;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;
import com.challenge.bookingapi.resource.dto.response.BookingDto;
import com.challenge.bookingapi.resource.dto.response.ResponseDto;
import com.challenge.bookingapi.service.IBookingService;
import com.challenge.bookingapi.service.ICustomerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.challenge.bookingapi.util.GenericMapper.map;
import static com.challenge.bookingapi.util.GenericMapper.mapCollection;
import static com.challenge.bookingapi.util.ResponseUtil.preparedErrorResponse;


@RestController
@RequestMapping("/booking")
@Log4j2
@Validated
public class BookingResource {

    private final IBookingService bookingService;

    private final ModelMapper modelMapper;


    public BookingResource(IBookingService bookingService, ICustomerService customerService,ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findAllBooking(){
        log.info(String.format("findAllBooking() - init at %s", LocalDate.now()));
        var response = new ResponseDto<Collection<BookingDto>>(LocalDateTime.now());
        try{
            this.bookingService.findAllBooking().ifPresent(item -> {
                response.setData(mapCollection(item, BookingDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
            return ResponseEntity.ok(response);
        }catch (BookingException be){
            log.error(String.format("Error getting all booking. Cause: %s", be.getMessage()));
            preparedErrorResponse(be.getMessage(), response);
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @GetMapping(value = "/cuscode/{customer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> findBookingByCustomerCode(@PathVariable("customer") Long customerCode){
        log.info("findBookingByCustomerCode() - init");
        var response = new ResponseDto<Collection<BookingDto>>(LocalDateTime.now());
        try{
            this.bookingService.findBookingByCustomerCode(customerCode).ifPresent(item -> {
                response.setData(mapCollection(item, BookingDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
            return ResponseEntity.ok(response);
        }catch (BookingException be){
            preparedErrorResponse(be.getMessage(), response);
            return ResponseEntity.internalServerError().body(response);

        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> saveBooking(@RequestBody BookingRequestDto bookingDto){
        log.info("saveABooking() -init");
        var response = new ResponseDto<BookingDto>(LocalDateTime.now());

         try{
            this.bookingService.saveBooking(bookingDto).ifPresent(item ->{
                response.setData(map(item, BookingDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
             return ResponseEntity.ok(response);
         }catch (BookingAppException e){
             preparedErrorResponse(e.getMessage(), response);
             return ResponseEntity.internalServerError().body(response);
         }
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto> cancelBooking(@RequestParam("bookingID") Long bookingID){
        log.info("saveABooking() -init");
        var response = new ResponseDto<Boolean>(LocalDateTime.now());

        try{
            this.bookingService.cancelBooking(bookingID).ifPresent(item ->{
                response.setData(item);
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
            return ResponseEntity.ok(response);
        }catch (BookingAppException e){
            preparedErrorResponse(e.getMessage(), response);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ResponseDto> updateBooking(@PathVariable @Valid Long bookingId, @RequestBody @Valid BookingRequestDto bookingRequestDto){
        log.info("saveABooking() -init");
        var response = new ResponseDto<BookingDto>(LocalDateTime.now());

        try{
            this.bookingService.updateBooking(bookingId, bookingRequestDto).ifPresent(item ->{
                response.setData(map(item, BookingDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
            return ResponseEntity.ok(response);
        }catch (BookingAppException e){
            preparedErrorResponse(e.getMessage(), response);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var response = new ResponseDto<BookingDto>(LocalDateTime.now());
        var fieldsErrors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            fieldsErrors.add(error.getDefaultMessage());
        });
        response.setMessage("Fields Validations Error");
        response.setErrors(fieldsErrors);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

}
