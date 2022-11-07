package com.challenge.bookingapi.resource.dto.response;

import com.challenge.bookingapi.entity.Country;
import com.challenge.bookingapi.entity.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties
public class HotelDto {

    private Long id;

    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String hotelCode;

    @JsonBackReference
    private Country country;

    @JsonManagedReference
    private List<Room> roomList;
}
