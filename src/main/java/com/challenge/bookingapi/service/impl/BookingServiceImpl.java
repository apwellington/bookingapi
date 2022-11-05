package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.repository.IBookingRepository;
import com.challenge.bookingapi.service.IBookingService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;

    @NonNull
    public BookingServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Optional<Collection<Booking>> findBookingByUserId(Long id) {
        return this.bookingRepository.findAllBookingByCustomerId(id);
    }

    @Override
    public Optional<Booking> saveBooking(Booking booking) {
        return Optional.of(this.bookingRepository.save(booking));
    }

    @Override
    public Optional<Booking> cancelBooking(String bookingCode) {
        return this.bookingRepository.cancelBookingByBookingCode(bookingCode);
    }

    @Override
    public Optional<Booking> updateBooking(Booking booking) {
        //Todo improve aproach
        Optional<Booking> oldBooking = this.bookingRepository.findById(booking.getId());
        oldBooking.ifPresent(this::saveBooking);
        return Optional.empty();
    }

}
