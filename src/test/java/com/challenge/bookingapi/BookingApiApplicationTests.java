package com.challenge.bookingapi;

import com.challenge.bookingapi.service.IRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingApiApplicationTests {

    @Autowired
    private IRoomService roomService;

    @Test
    void contextLoads() {
        this.roomService.findById(1L);
    }

}
