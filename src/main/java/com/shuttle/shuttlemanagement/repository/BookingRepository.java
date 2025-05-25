package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.Booking;
import com.shuttle.shuttlemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}