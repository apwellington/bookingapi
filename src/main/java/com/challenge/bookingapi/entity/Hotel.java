package com.challenge.bookingapi.entity;


import com.challenge.bookingapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class Hotel extends BaseEntity {
    private String name;
    private String hotelCode;

    @OneToOne
    private Country country;
    @OneToMany
    private List<Room> roomList;

}
