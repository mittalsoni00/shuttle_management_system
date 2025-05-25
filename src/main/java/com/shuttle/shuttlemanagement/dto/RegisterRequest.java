package com.shuttle.shuttlemanagement.dto;

import lombok.*;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // "USER" or "ADMIN"
}