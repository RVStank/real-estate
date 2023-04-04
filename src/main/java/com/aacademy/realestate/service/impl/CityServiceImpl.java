package com.aacademy.realestate.service.impl;

import com.aacademy.realestate.exception.ResourceNotFoundException;
import com.aacademy.realestate.model.City;
import com.aacademy.realestate.model.Neighborhood;
import com.aacademy.realestate.repository.CityRepository;
import com.aacademy.realestate.service.CityService;
import com.aacademy.realestate.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final NeighborhoodService neighborhoodService;

    public CityServiceImpl(CityRepository cityRepository, NeighborhoodService neighborhoodService) {
        this.cityRepository = cityRepository;
        this.neighborhoodService = neighborhoodService;
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public City save(City city) {
        Set<Neighborhood> neighborhoods = new HashSet<>();

        for(Neighborhood neighborhood : city.getNeighborhoods()) {
            Neighborhood foundNeighborhood = neighborhoodService.findById(neighborhood.getId());
        }

        cityRepository.save(City.builder()
                .neighborhoods(neighborhoods)
                .name(city.getName())
                .build());

        return null;

    }

    @Override
    public Set<City> findAll() {
        return null;
    }
}
