package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String isoCode3;

    @OneToMany(mappedBy = "country")
    private List<Hotel> hotelList;


}
