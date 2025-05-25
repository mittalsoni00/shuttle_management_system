package com.shuttle.shuttlemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StopUsageResponse {
    private String userEmail;
    private String stopName;
    private LocalDateTime timestamp;
}