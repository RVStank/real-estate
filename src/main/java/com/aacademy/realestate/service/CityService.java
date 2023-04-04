package com.aacademy.realestate.service;

import com.aacademy.realestate.model.City;

import java.util.Set;

public interface CityService {

    City findById(Long id);

    City save(City city);

    Set<City> findAll();
}
