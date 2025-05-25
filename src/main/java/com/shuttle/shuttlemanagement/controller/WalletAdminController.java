package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.service.WalletAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/wallet")
@RequiredArgsConstructor
public class WalletAdminController {

    private final WalletAdminService walletAdminService;

    @PostMapping("/allocate")
    public ResponseEntity<?> allocate(@RequestParam Long userId, @RequestParam double amount) {
        walletAdminService.allocatePoints(userId, amount);
        return ResponseEntity.ok("Points allocated to user ID: " + userId);
    }

    @PostMapping("/deduct")
    public ResponseEntity<?> deduct(@RequestParam Long userId, @RequestParam double amount) {
        walletAdminService.deductPoints(userId, amount);
        return ResponseEntity.ok("Points deducted from user ID: " + userId);
    }

    @PostMapping("/recharge")
    public ResponseEntity<?> recharge(@RequestParam Long userId, @RequestParam double amount) {
        walletAdminService.recharge(userId, amount);
        return ResponseEntity.ok("Wallet recharged for user ID: " + userId);
    }
}