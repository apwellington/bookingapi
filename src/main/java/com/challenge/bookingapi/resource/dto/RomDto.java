package com.challenge.bookingapi.resource.dto;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class RomDto {
    private String description;
    private String number;
    @JsonBackReference
    private Hotel hotel;
    @JsonBackReference
    private Booking booking;
    private Date createdAt;
    private Date updatedAt;
}
