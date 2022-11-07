package com.challenge.bookingapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID")
    private Long countryId;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String isoCode3;

    @OneToMany(mappedBy = "country")
    private List<Hotel> hotelList;


}
