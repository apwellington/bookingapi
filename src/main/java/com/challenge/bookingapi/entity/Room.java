package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String description;

    @ManyToOne
    private Hotel hotel;

    @OneToOne(mappedBy = "room")
    private Booking booking;


    private Date createdAt;
    private Date updatedAt;
}
