package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.Shuttle;
import com.shuttle.shuttlemanagement.entity.ShuttleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShuttleRepository extends JpaRepository<Shuttle, Long> {
    List<Shuttle> findByStatus(ShuttleStatus status);
}