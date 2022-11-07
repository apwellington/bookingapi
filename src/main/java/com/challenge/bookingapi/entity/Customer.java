package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany
    private Collection<Booking> booking;
}
