package com.challenge.bookingapi.entity;

import com.challenge.bookingapi.entity.base.BaseEntity;
import com.challenge.bookingapi.util.BookingStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Entity
public class Booking extends BaseEntity {
    private String detail;
    private String description;
    private String bookingCode;
    @OneToOne
    private User user;
    @OneToOne
    private Room room;
    private BookingStatus bookingStatus;
    private Date fromDate;
    private Date toDate;
}
