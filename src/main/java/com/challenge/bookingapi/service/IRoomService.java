package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Room;
import com.challenge.bookingapi.exception.RoomException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


public interface IRoomService {

    Optional<Collection<Room>> findAvailableRoomsByDateRage(Long hotelId, Date dateTo, Date dateFrom) throws RoomException;
    Optional<Room> findById(Long id) throws RoomException;
}
