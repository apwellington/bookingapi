package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Long roomId;
    private String description;
    private String number;

    @ManyToOne
    @JoinColumn(name = "HOTEl_ID")
    private Hotel hotel;

    @OneToOne(mappedBy = "room")
    private Booking booking;


    private Date createdAt;
    private Date updatedAt;
}
