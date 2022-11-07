package com.challenge.bookingapi.util;

import com.challenge.bookingapi.resource.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    public static void preparedErrorResponse(String message, ResponseDto response){
        response.setMessage(message);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static void preparedSuccessResponse(String message, ResponseDto response){
        return;
    }
}
