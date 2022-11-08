package com.challenge.bookingapi.repository;

import com.challenge.bookingapi.entity.Room;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface IRoomRepository  extends JpaRepository<Room, Long> {
    @Query("SELECT R FROM Room R WHERE R.hotel.hotelId = :hotelId AND R.roomId NOT IN (SELECT B.room.roomId FROM Booking B WHERE  B.toDate > :toDate AND  B.fromDate < :fromDate)")
    Optional<Collection<Room>> findAllByHotelAndDateRange(@Param("hotelId") Long hotelId, @Param("toDate") Date toDate, @Param("fromDate") Date fromDate);
}

