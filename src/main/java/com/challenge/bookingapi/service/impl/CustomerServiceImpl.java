package com.challenge.bookingapi.service.impl;

import com.challenge.bookingapi.entity.Customer;
import com.challenge.bookingapi.exception.CustomerException;
import com.challenge.bookingapi.repository.ICustomerRepository;
import com.challenge.bookingapi.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) throws CustomerException {
        return this.customerRepository.findById(id);
    }
}
