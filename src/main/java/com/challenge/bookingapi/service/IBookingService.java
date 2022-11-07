package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;
import com.challenge.bookingapi.resource.dto.response.BookingDto;

import java.util.Collection;
import java.util.Optional;

public interface IBookingService{
    Optional<Collection<Booking>> findBookingByCustomerCode(Long customerCode) throws BookingException;

    Optional<Collection<Booking>> findAllBooking() throws BookingException;
    Optional<Booking> saveBooking(BookingRequestDto booking ) throws BookingException;
    Optional<Boolean> cancelBooking(Long bookingId)  throws BookingException;
    Optional<Booking> updateBooking(Long bookingId, BookingRequestDto booking)  throws BookingException;;
}
