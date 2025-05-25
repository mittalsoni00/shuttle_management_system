package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.Trip;
import com.shuttle.shuttlemanagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByFromCityAndToCity(City from, City to);
}