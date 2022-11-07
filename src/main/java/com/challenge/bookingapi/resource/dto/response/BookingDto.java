package com.challenge.bookingapi.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class BookingDto {
    private Long bookingId;
    private String detail;
    private String description;
    private Date fromDate;
    private Date toDate;
    private Boolean bookingActived;
    @JsonManagedReference
    private CustomerDto customer;
    @JsonManagedReference
    private RoomDto room;
    private Date createdAt;
    private Date updatedAt;
}
