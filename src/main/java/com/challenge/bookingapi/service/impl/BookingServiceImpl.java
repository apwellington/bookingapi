package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingAppException;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.exception.CustomerException;
import com.challenge.bookingapi.exception.RoomException;
import com.challenge.bookingapi.repository.IBookingRepository;
import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;
import com.challenge.bookingapi.service.IBookingService;
import com.challenge.bookingapi.service.ICustomerService;
import com.challenge.bookingapi.service.IRoomService;
import com.challenge.bookingapi.util.GenericMapper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final ICustomerService customerService;

    private final IRoomService roomService;


    @NonNull
    public BookingServiceImpl(IBookingRepository bookingRepository, ICustomerService customerService, IRoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @Override
    public Optional<Collection<Booking>> findBookingByCustomerCode(Long customerCode) {
        return this.bookingRepository.findAllBookingByCustomerCode(customerCode);
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
    public Optional<Booking> saveBooking(BookingRequestDto booking) throws BookingException {

        try {
            Booking newBooking = new Booking();
            this.customerService.findCustomerById(booking.getCustomerId()).ifPresent(newBooking::setCustomer);
            this.roomService.findById(booking.getRoomId()).ifPresent(newBooking::setRoom);
            newBooking.setCreatedAt(new Date());
            newBooking.setBookingActived(true);
            newBooking.setDetail(booking.getDetail());
            newBooking.setDescription(booking.getDescription());
            newBooking.setFromDate(booking.getFromDate());
            newBooking.setToDate(booking.getToDate());
            return Optional.of(this.bookingRepository.save(newBooking));
        }catch (BookingAppException e){
            throw new BookingException("");
        }

    }

    @Override
    public Optional<Booking> cancelBooking(Long bookingId) {
        return this.bookingRepository.cancelBookingByBookingCode(bookingId);
    }

    @Override
    public Optional<Booking> updateBooking(Booking booking) {
        Optional<Booking> currentBooking = this.bookingRepository.findById(booking.getBookingId());
        if(!(currentBooking.isPresent())) {
           // throw new BooKin
        }
        return null;
    }

}
