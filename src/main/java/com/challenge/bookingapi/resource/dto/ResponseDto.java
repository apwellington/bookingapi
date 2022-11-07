package com.challenge.bookingapi.resource.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {
    private Integer statusCode;
    private String message;
    private LocalDateTime date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
