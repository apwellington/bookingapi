package com.challenge.bookingapi.resource.dto;

import com.challenge.bookingapi.util.BookingStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookingDto implements Serializable {
    private String detail;
    private String description;
    private String bookingCode;
    private Date fromDate;
    private Date toDate;
    private BookingStatus bookingStatus;
    @JsonManagedReference
    private CustomerDto customer;
    @JsonManagedReference
    private RomDto room;
    private Date createdAt;
    private Date updatedAt;
}
