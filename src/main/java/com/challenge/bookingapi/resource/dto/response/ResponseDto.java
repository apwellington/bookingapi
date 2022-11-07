package com.challenge.bookingapi.resource.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
    private Integer statusCode;
    private String message;
    private LocalDateTime date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDto(LocalDateTime date){
        this.date = date;
    }
}
