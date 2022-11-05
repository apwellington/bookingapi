package com.challenge.bookingapi.resource;

import com.challenge.bookingapi.entity.Room;
import com.challenge.bookingapi.service.IRoomService;
import com.challenge.bookingapi.service.impl.RoomServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomResource {

    private final RoomServiceImpl roomService;

    public RoomResource(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public List<Room> findAll(){
        return this.roomService.findAll();
    }
}
