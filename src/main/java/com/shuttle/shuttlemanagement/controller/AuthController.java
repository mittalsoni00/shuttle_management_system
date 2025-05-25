package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.dto.*;
import com.shuttle.shuttlemanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        if (!isUniversityEmail(request.getEmail())) {
            throw new RuntimeException("Please use your university email (ending with @bennett.edu.in) to register.");
        }
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        if (!isUniversityEmail(request.getEmail())) {
            throw new RuntimeException("Please use your university email (ending with @bennett.edu.in) to login.");
        }
        return authService.login(request);
    }
    private boolean isUniversityEmail(String email) {
        return email.toLowerCase().endsWith("@bennett.edu.in");
    }
}