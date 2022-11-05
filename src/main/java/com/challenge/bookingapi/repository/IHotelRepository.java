package com.challenge.bookingapi.repository;

import com.challenge.bookingapi.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository  extends JpaRepository<Hotel, Long> {
}
