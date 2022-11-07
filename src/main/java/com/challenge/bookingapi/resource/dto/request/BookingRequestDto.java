package com.challenge.bookingapi.resource.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookingRequestDto {
    private String detail;
    private String description;
    private Date fromDate;
    private Date toDate;
    private Long customerId;
    @NotNull(message = "roomId is required")
    private Long roomId;
}
