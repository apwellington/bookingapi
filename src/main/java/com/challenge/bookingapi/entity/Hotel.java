package com.challenge.bookingapi.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String hotelCode;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @OneToMany(mappedBy = "hotel")
    private List<Room> roomList;

}
