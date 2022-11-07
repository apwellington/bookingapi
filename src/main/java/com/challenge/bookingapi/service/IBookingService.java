package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingException;

import java.util.Collection;
import java.util.Optional;

public interface IBookingService{
    Optional<Collection<Booking>> findBookingByUserId(Long userId);

    Optional<Collection<Booking>> findAllBooking() throws BookingException;
    Optional<Booking> saveBooking(Booking booking);
    Optional<Booking> cancelBooking(String bookingCode);
    Optional<Booking> updateBooking(Booking booking);
}
