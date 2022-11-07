package com.challenge.bookingapi.util;

public enum BookingOperation {
    CREATE("CREATE"), CANCEL("CANCEL");

    public String value;

    BookingOperation(String value) {
        this.value = value;
    }
}
