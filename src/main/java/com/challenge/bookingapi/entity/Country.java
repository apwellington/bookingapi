package com.challenge.bookingapi.entity;

import com.challenge.bookingapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Country extends BaseEntity {
    private String name;
    private String isoCode3;
}
