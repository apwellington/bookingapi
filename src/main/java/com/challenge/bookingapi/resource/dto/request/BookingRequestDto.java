package com.challenge.bookingapi.resource.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookingRequestDto {
    private String detail;
    private String description;
    private Date fromDate;
    private Date toDate;
    private Long customerId;
    private Long roomId;
}
