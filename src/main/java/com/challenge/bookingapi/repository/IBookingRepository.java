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
    @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerCode")
    Optional<Collection<Booking>> findAllBookingByCustomerCode(@Param("customerCode") Long customerCode);

    @Query("UPDATE Booking b SET b.bookingActived = 'false' WHERE b.id = :id")
    Optional<Booking> cancelBookingByBookingCode(@Param("id") Long id);

}
