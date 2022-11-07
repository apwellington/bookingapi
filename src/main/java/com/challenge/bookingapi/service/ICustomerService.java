package com.challenge.bookingapi.service;

import com.challenge.bookingapi.entity.Customer;
import com.challenge.bookingapi.exception.CustomerException;

import java.util.Optional;

public interface ICustomerService {

    Optional<Customer> findCustomerById(Long id) throws CustomerException;
}
