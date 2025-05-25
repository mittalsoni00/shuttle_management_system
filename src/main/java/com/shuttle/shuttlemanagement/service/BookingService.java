package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.entity.Booking;
import com.shuttle.shuttlemanagement.entity.Trip;
import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.repository.BookingRepository;
import com.shuttle.shuttlemanagement.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;
    private final WalletService walletService;
    private final FareService fareService;
    private final WalletAdminService walletAdminService;

    public Booking createBooking(User user, Long tripId, double fare) {
        // 1. Fetch trip
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // 2. Calculate dynamic fare
        fare = fareService.calculateFare(LocalDateTime.now());

        // 3. Deduct fare from wallet
        walletService.deductFunds(user, fare); // Throws exception if insufficient

        // 4. Save booking
        Booking booking = Booking.builder()
                .user(user)
                .trip(trip)
                .bookingTime(LocalDateTime.now())
                .build();

        return bookingRepository.save(booking);
    }
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (isLateCancellation(booking)) {
            walletAdminService.deductPoints(booking.getUser().getId(), 20.0); // penalty
        }

        bookingRepository.delete(booking);
    }
    private boolean isLateCancellation(Booking booking) {
        return booking.getTrip().getStartTime().minusMinutes(10).isBefore(LocalDateTime.now());
    }
}