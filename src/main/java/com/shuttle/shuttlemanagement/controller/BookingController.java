package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.entity.Booking;
import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.service.BookingService;
import com.shuttle.shuttlemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService; // Assuming you can get user from token

    @PostMapping("/create")
    public ResponseEntity<?> bookTrip(@RequestParam Long tripId, @RequestParam double fare) {
        User currentUser = userService.getCurrentUser(); // You need to implement this
        Booking booking = bookingService.createBooking(currentUser, tripId, fare);
        return ResponseEntity.ok(booking);
    }
}
