package com.challenge.bookingapi.exception;

import org.hibernate.PersistentObjectException;

public class BookingAppException extends PersistentObjectException {
    public BookingAppException(String message) {
        super(message);
    }
}
