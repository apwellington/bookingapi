package com.challenge.bookingapi.repository;

import com.challenge.bookingapi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface IRoomRepository  extends JpaRepository<Room, Long> {
}
