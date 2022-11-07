package com.challenge.bookingapi.exception;

public class BookingCancellationException extends BookingAppException{
    public BookingCancellationException(String message) {
        super(message);
    }
}
