package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.repository.StopUsageRepository;
import com.shuttle.shuttlemanagement.repository.UserRepository;
import com.shuttle.shuttlemanagement.service.UserService;
import com.shuttle.shuttlemanagement.service.WalletAdminService;
import com.shuttle.shuttlemanagement.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
@RequiredArgsConstructor
public class AdminViolationController {

    private final StopUsageRepository stopUsageRepository;
    private final WalletService walletService;
    private final UserService userService;

    @PostMapping("/check-violation")
    public ResponseEntity<?> checkViolation(@RequestParam Long userId,
                                            @RequestParam Long stopId,
                                            @RequestParam String scheduledTimeStr) {
        LocalDateTime scheduledTime = LocalDateTime.parse(scheduledTimeStr);
        boolean checkedIn = stopUsageRepository.existsByUserIdAndStopIdAndTimestampBetween(
                userId, stopId,
                scheduledTime.minusMinutes(5),
                scheduledTime.plusMinutes(5)
        );
        if (!checkedIn) {
            walletService.deductFunds(userService.getUserById(userId), 20); // or any penalty
            return ResponseEntity.ok("Violation: Points deducted");
        }
        return ResponseEntity.ok("Checked in on time");
    }
}
