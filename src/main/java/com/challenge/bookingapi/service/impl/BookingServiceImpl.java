package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.repository.IBookingRepository;
import com.challenge.bookingapi.service.IBookingService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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
    public Optional<Collection<Booking>> findAllBooking() throws BookingException {
        log.info("findAllBooking() init");
        try{
            Collection<Booking> result = this.bookingRepository.findAll();
            return Optional.of(result).or(() -> Optional.of(new ArrayList<Booking>()));
        }catch (RuntimeException e){
            log.error(e);
            throw new BookingException("Error trying to get bookings");
        }
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
        Optional<Booking> currentBooking = this.bookingRepository.findById(booking.getId());
        if(!(currentBooking.isPresent())) {
           // throw new BooKin
        }
        return this.saveBooking(booking);
    }

}
