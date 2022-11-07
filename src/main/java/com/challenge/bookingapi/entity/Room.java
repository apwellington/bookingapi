package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;
    private String description;
    private String number;

    @ManyToOne
    @JoinColumn(name = "HOTEl_ID")
    private Hotel hotel;

    @OneToOne
    private Booking booking;


    private Date createdAt;
    private Date updatedAt;
}
