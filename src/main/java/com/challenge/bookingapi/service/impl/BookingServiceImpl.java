package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingAppException;
import com.challenge.bookingapi.exception.BookingException;
import com.challenge.bookingapi.exception.CustomerException;
import com.challenge.bookingapi.repository.IBookingRepository;
import com.challenge.bookingapi.resource.dto.request.BookingRequestDto;
import com.challenge.bookingapi.resource.dto.response.BookingDto;
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

    private final ModelMapper modelMapper;


    @NonNull
    public BookingServiceImpl(IBookingRepository bookingRepository, ICustomerService customerService, IRoomService roomService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Collection<Booking>> findBookingByCustomerCode(Long customerCode) {
        return this.bookingRepository.findAllBookingByCustomerCode(customerCode);
    }

    @Override
    public Optional<Collection<Booking>> findAllBooking() throws BookingException {
        log.info("findAllBooking() init");
        try {
            Collection<Booking> result = this.bookingRepository.findAll();
            return Optional.of(result).or(() -> Optional.of(new ArrayList<Booking>()));
        } catch (RuntimeException e) {
            log.error(e);
            throw new BookingException("Error trying to get bookings");
        }
    }

    @Override
    public Optional<Booking> saveBooking(BookingRequestDto booking) throws BookingException {

        try {
            Booking newBooking = GenericMapper.map(booking, Booking.class, this.modelMapper);
            newBooking.setCustomer(this.customerService.findCustomerById(booking.getCustomerId()).orElseThrow(() -> new CustomerException("Can not find Customer")));
            newBooking.setRoom(this.roomService.findById(booking.getRoomId()).orElseThrow(() -> new CustomerException("Can not find Room")));
            return Optional.of(this.bookingRepository.save(newBooking));
        } catch (BookingAppException e) {
            throw new BookingException(e.getMessage());
        }

    }

    @Override
    public Optional<Boolean> cancelBooking(Long bookingId) throws BookingException {
        return this.bookingRepository
                .findById(bookingId)
                .map(item -> {
                    item.setBookingActived(false);
                    this.bookingRepository.save(item);
                    return Optional.of(true);
                }).orElse(Optional.of(false));
    }

    @Override
    public Optional<Booking> updateBooking(Long bookingId, BookingRequestDto booking) throws BookingException {

        return this.bookingRepository.findById(bookingId).map(element -> {
                    element.setDetail(booking.getDetail());
                    element.setDescription(booking.getDescription());
                    element.setFromDate(booking.getFromDate());
                    element.setToDate(booking.getToDate());
                    element.setCustomer(this.customerService.findCustomerById(booking.getCustomerId()).orElseThrow(() -> new CustomerException("Can not find Customer")));
                    element.setRoom(this.roomService.findById(booking.getRoomId()).orElseThrow(() -> new CustomerException("Can not find Room")));
                    element.setUpdatedAt(new Date());
                    return Optional.of(this.bookingRepository.save(element));
                })
                .orElseThrow(() -> new BookingException(""));
    }

}
