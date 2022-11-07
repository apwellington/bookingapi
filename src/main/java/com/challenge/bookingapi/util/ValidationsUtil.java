package com.challenge.bookingapi.util;

import com.challenge.bookingapi.entity.Booking;
import com.challenge.bookingapi.exception.BookingCancellationException;
import com.challenge.bookingapi.exception.BookingReservationException;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ValidationsUtil {

    public static final Integer LIMIT_OF_HOURS_BOOKING_CANCELLATION = 24;
    public static final Integer LIMIT_OF_HOURS_BOOKING_RESERVATION = 24;
    public static final Integer LIMIT_OF_MONTH_BOOKING_CHECK_IN = 1;
    public static final Integer LIMIT_OF_DAYS_OF_RESERVATION = 5;

    public static void validateBookingOperation(Booking booking, BookingOperation operation) throws BookingCancellationException {
       LocalDate bookingDate = Instant.ofEpochMilli(booking.getFromDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
       LocalDate checkOutDate = Instant.ofEpochMilli(booking.getToDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

       LocalDate currentDate = LocalDate.now();
       Duration duration = Duration.between( currentDate.atStartOfDay(), bookingDate.atStartOfDay());
       if(operation.value.equals(BookingOperation.CANCEL.value)){
           if(currentDate.isAfter(bookingDate)){
               throw new BookingCancellationException("Can not cancel your booking while the booking is running");
           }

           if( duration.toHours() <= ValidationsUtil.LIMIT_OF_HOURS_BOOKING_CANCELLATION){
               throw new BookingCancellationException("Can not cancel your booking before 24 hours");
           }
       }

       if(operation.value.equals(BookingOperation.CREATE.value)){

           if(duration.toHours() < ValidationsUtil.LIMIT_OF_HOURS_BOOKING_RESERVATION){
               throw new BookingReservationException("Can not make reservation 24 hours before the check-in date ");
           }

           if(currentDate.until(checkOutDate, ChronoUnit.MONTHS) > LIMIT_OF_MONTH_BOOKING_CHECK_IN){
               throw new BookingReservationException("Can not make reservation more than 1 month before the check-in date ");
           }

           if(bookingDate.until(checkOutDate, ChronoUnit.DAYS) > ValidationsUtil.LIMIT_OF_DAYS_OF_RESERVATION ){
               throw new BookingReservationException("Can not book more than 5 days");
           }
       }
    }

}
