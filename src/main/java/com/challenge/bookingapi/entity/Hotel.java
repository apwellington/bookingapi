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
    @Column(name = "id")
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String hotelCode;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "hotel")
    private List<Room> roomList;

}
