package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Room;
import com.challenge.bookingapi.exception.RoomException;
import com.challenge.bookingapi.repository.IRoomRepository;
import com.challenge.bookingapi.service.IRoomService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements IRoomService {

    private final IRoomRepository roomRepository;

    @NonNull
    public RoomServiceImpl(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Collection<Room>> findAvailableRoomsByDateRage(Long hotelId, Date dateTo, Date dateFrom) throws RoomException {
        return this.roomRepository.findAllByHotelAndDateRange(hotelId, dateTo, dateFrom);
    }

    @Override
    public Optional<Room> findById(Long id) throws RoomException {
        return this.roomRepository.findById(id);
    }
}
