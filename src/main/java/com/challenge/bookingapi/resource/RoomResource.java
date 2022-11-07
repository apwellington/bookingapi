package com.challenge.bookingapi.resource;

import com.challenge.bookingapi.exception.RoomException;
import com.challenge.bookingapi.resource.dto.response.ResponseDto;
import com.challenge.bookingapi.resource.dto.response.RoomDto;
import com.challenge.bookingapi.service.impl.RoomServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import static com.challenge.bookingapi.util.GenericMapper.mapCollection;
import static com.challenge.bookingapi.util.ResponseUtil.preparedErrorResponse;

@RestController
@RequestMapping("/room")
@Log4j2
public class RoomResource {

    private final RoomServiceImpl roomService;
    private final ModelMapper modelMapper;


    public RoomResource(RoomServiceImpl roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }
    @GetMapping(value = {"","/"})
    public ResponseEntity<ResponseDto> findByHotelAndDateRange(
            @RequestParam("hotelId") Long hotelId,
            @RequestParam("dateFrom") Date dateFrom,
            @RequestParam("dateTo")  Date dateTo)
    {
        log.info("findByHotelAndDateRange() - init");
        var response = new ResponseDto<Collection<RoomDto>>(LocalDateTime.now());
        try{
            this.roomService.findAvailableRoomsByDateRage(hotelId, dateTo, dateFrom).ifPresent(item -> {
                response.setData(mapCollection(item, RoomDto.class, modelMapper));
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
            });
            return ResponseEntity.ok(response);
        }catch (RoomException be){
            preparedErrorResponse(be.getMessage(), response);
            return ResponseEntity.internalServerError().body(response);
        }
    }

}
