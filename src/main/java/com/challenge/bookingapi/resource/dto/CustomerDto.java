package com.challenge.bookingapi.resource.dto;

import com.challenge.bookingapi.entity.Booking;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    @JsonBackReference
    private Booking booking;
}
