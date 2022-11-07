package com.challenge.bookingapi.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GenericMapper {
    public static <T, R> R map(T t, Class<R> rClass, ModelMapper mapper){
        return mapper.map(t, rClass);
    }

    public static <T, R> List<R> mapCollection(Collection<T> t, Class<R> rClass, ModelMapper mapper){
        return t.stream().map(item -> mapper.map(item, rClass)).collect(Collectors.toList());
    }
}
