package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Room;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;


public interface IRoomService {

    Optional<Collection<Room>> getAvailableRoomsByDateRage(Date dateFrom, Date dateTo, String hotelCode);
}
