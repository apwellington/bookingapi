package com.challenge.bookingapi;

import com.challenge.bookingapi.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingRepositoryTest {

    @Autowired
    IBookingRepository bookingRepository;


}
