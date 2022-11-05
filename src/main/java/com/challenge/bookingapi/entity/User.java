package com.challenge.bookingapi.entity;

import com.challenge.bookingapi.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
}
