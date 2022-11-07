package com.challenge.bookingapi.resource.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
    private Integer statusCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
    private LocalDateTime date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDto(LocalDateTime date){
        this.date = date;
    }
}
