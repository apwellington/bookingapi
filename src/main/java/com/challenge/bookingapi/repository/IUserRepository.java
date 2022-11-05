package com.challenge.bookingapi.repository;

import com.challenge.bookingapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<Customer, Long> {
}
