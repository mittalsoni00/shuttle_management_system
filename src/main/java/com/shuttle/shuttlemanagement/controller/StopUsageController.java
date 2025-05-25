package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.dto.StopUsageResponse;
import com.shuttle.shuttlemanagement.service.StopUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/usage")
@RequiredArgsConstructor
public class StopUsageController {
    private final StopUsageService stopUsageService;

    // Endpoint for admin to see stop demand stats
    @GetMapping("/stats")
    public ResponseEntity<List<Map<String, Object>>> getUsageStats() {
        return ResponseEntity.ok(stopUsageService.getUsageStats());
    }
    @GetMapping("/logs")
    public ResponseEntity<List<StopUsageResponse>> getAllLogs() {
        return ResponseEntity.ok(stopUsageService.getAllUsageLogs());
    }
    @PostMapping("/log")
    public ResponseEntity logStopUsage(@RequestParam Long userId, @RequestParam Long stopId) {
        stopUsageService.logUsage(userId, stopId);
        return ResponseEntity.ok("Usage recorded");
    }
}