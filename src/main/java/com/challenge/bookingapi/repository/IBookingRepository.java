package com.challenge.bookingapi.repository;

import com.challenge.bookingapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b where b.user.id = :userID")
    Optional<Collection<Booking>> findAllBookingByCustomerId(@Param("userID") Long userID);

    @Query("UPDATE Booking b SET b.bookingStatus = 'CANCELED' WHERE b.bookingCode = :bookingCode")
    Optional<Booking> cancelBookingByBookingCode(@Param("bookingCode") String bookingCode);

}
