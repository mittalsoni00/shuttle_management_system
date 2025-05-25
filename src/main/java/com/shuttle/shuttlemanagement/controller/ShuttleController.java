package com.shuttle.shuttlemanagement.controller;

import com.shuttle.shuttlemanagement.dto.ShuttleStatusUpdateRequest;
import com.shuttle.shuttlemanagement.entity.Shuttle;
import com.shuttle.shuttlemanagement.service.ShuttleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/shuttles")
@RequiredArgsConstructor
public class ShuttleController {
    private final ShuttleService shuttleService;

    @PostMapping("/add")
    public ResponseEntity<Shuttle> addShuttle(@RequestParam String name, @RequestParam Long routeId) {
        return ResponseEntity.ok(shuttleService.addShuttle(name, routeId));
    }

    @PostMapping("/location")
    public ResponseEntity<Shuttle> updateLocation(@RequestParam Long id, @RequestParam double lat, @RequestParam double lon) {
        return ResponseEntity.ok(shuttleService.updateLocation(id, lat, lon));
    }

    @GetMapping
    public ResponseEntity<List<Shuttle>> getAll() {
        return ResponseEntity.ok(shuttleService.getAll());
    }
    @PostMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody ShuttleStatusUpdateRequest request) {
        shuttleService.updateStatus(request);
        return ResponseEntity.ok("Shuttle status updated");
    }
    @GetMapping("/status")
    public ResponseEntity<List<Shuttle>> getAllShuttlesStatus() {
        return ResponseEntity.ok(shuttleService.getAllShuttles());
    }

    @GetMapping("/monitor")
    public ResponseEntity<List<Map<String, Object>>> monitorShuttles() {
        return ResponseEntity.ok(shuttleService.getMonitoringData());
    }
    //Admin Trigger to Reallocate Shuttles
    @PostMapping("/auto-allocate")
    public ResponseEntity<String> autoAllocate() {
        shuttleService.autoAllocateIdleShuttles();
        return ResponseEntity.ok("Idle shuttles reallocated to high-demand stop.");
    }
}