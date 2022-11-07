package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;

import java.util.Collection;
import java.util.Optional;

public interface IBookingService{
    Optional<Collection<Booking>> findBookingByCustomerCode(Long customerCode) throws BookingException;

    Optional<Collection<Booking>> findAllBooking() throws BookingException;
    Optional<Booking> saveBooking(BookingRequestDto booking ) throws BookingException;
    Optional<Booking> cancelBooking(Long bookingId);
    Optional<Booking> updateBooking(Booking booking);
}
