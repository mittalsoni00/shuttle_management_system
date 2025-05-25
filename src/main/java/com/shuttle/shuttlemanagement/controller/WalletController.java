package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.security.CustomUserDetailsService;
import com.shuttle.shuttlemanagement.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(walletService.getBalance(user));
    }

    @PostMapping("/recharge")
    public ResponseEntity<String> recharge(@AuthenticationPrincipal User user,
                                           @RequestParam double amount) {
        walletService.addFunds(user, amount);
        return ResponseEntity.ok("Wallet recharged with ₹" + amount);
    }
}