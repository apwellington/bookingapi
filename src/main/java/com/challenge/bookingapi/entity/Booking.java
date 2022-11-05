package com.challenge.bookingapi.entity;

import com.challenge.bookingapi.util.BookingStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Booking  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String detail;
    private String description;
    private String bookingCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    private BookingStatus bookingStatus;
    private Date fromDate;
    private Date toDate;
}
