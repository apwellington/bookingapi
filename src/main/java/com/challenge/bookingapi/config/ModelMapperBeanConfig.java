package com.challenge.bookingapi.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBeanConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
