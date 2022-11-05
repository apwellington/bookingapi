package com.challenge.bookingapi.util;

public enum BookingStatus {
    APPROVED("APPROVED"),
    PENDING("PENDING"),
    CANCELED("PENDING");
    private String value;

    BookingStatus(String value) {
        this.value = value;
    }
}
