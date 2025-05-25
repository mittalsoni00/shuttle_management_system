package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}