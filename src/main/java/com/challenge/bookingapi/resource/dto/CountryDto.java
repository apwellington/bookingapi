package com.challenge.bookingapi.resource.dto;

import com.challenge.bookingapi.entity.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties
public class CountryDto {
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String isoCode3;
    @JsonManagedReference
    private List<Hotel> hotelList;
}
