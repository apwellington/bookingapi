package com.challenge.bookingapi.entity;

import com.challenge.bookingapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Room extends BaseEntity {
    private String description;
    @OneToOne
    private Hotel hotel;
}
