package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.entity.Stop;
import com.shuttle.shuttlemanagement.entity.Trip;
import com.shuttle.shuttlemanagement.repository.StopRepository;
import com.shuttle.shuttlemanagement.repository.StopUsageRepository;
import com.shuttle.shuttlemanagement.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoShowDetectionService {
    private final TripRepository tripRepository;
    private final StopRepository stopRepository;
    private final StopUsageRepository stopUsageRepository;
    private final WalletAdminService walletAdminService;

    public void checkNoShowAndPenalize(Long userId, Long tripId, Long stopId) {
        // Fetch scheduled trip time
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        LocalDateTime scheduledTime = trip.getStartTime(); // Update this if you have per-stop timing

        // Check if user checked in near this time (±5 minutes)
        boolean checkedIn = stopUsageRepository.existsByUserIdAndStopIdAndTimestampBetween(
                userId, stopId,
                scheduledTime.minusMinutes(5),
                scheduledTime.plusMinutes(5)
        );

        if (!checkedIn) {
            walletAdminService.deductPoints(userId, 10.0); // Deduct penalty
            // You can also log this event in a NoShow table
        }
    }
}
