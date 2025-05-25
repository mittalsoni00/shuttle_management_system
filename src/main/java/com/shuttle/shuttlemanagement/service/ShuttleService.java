package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.dto.ShuttleStatusUpdateRequest;
import com.shuttle.shuttlemanagement.entity.Shuttle;
import com.shuttle.shuttlemanagement.entity.Route;
import com.shuttle.shuttlemanagement.entity.ShuttleStatus;
import com.shuttle.shuttlemanagement.entity.Stop;
import com.shuttle.shuttlemanagement.repository.ShuttleRepository;
import com.shuttle.shuttlemanagement.repository.RouteRepository;
import com.shuttle.shuttlemanagement.repository.StopRepository;
import com.shuttle.shuttlemanagement.repository.StopUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShuttleService {
    private final ShuttleRepository shuttleRepository;
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final StopUsageRepository stopUsageRepository;

    public Shuttle addShuttle(String name, Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        Shuttle shuttle = Shuttle.builder()
                .name(name)
                .status(ShuttleStatus.IDLE)
                .latitude(0.0)
                .longitude(0.0)
                .route(route)
                .build();
        return shuttleRepository.save(shuttle);
    }

    public Shuttle updateLocation(Long id, double lat, double lon) {
        Shuttle shuttle = shuttleRepository.findById(id).orElseThrow(() -> new RuntimeException("Shuttle not found"));
        shuttle.setLatitude(lat);
        shuttle.setLongitude(lon);
        shuttle.setStatus(ShuttleStatus.ON_ROUTE);
        return shuttleRepository.save(shuttle);
    }

    public List<Shuttle> getAll() {
        return shuttleRepository.findAll();
    }
    public void updateStatus(ShuttleStatusUpdateRequest request) {
        Shuttle shuttle = shuttleRepository.findById(request.getShuttleId())
                .orElseThrow(() -> new RuntimeException("Shuttle not found"));

        shuttle.setStatus(ShuttleStatus.valueOf(request.getStatus2()));

        if (request.getStopId() != null) {
            Stop stop = stopRepository.findById(request.getStopId())
                    .orElseThrow(() -> new RuntimeException("Stop not found"));
            shuttle.setCurrentStop(stop);
        }

        shuttleRepository.save(shuttle);
    }
    public List<Shuttle> getAllShuttles() {
        return shuttleRepository.findAll();
    } //same as above already initialized


    public List<Map<String, Object>> getMonitoringData() {
        List<Shuttle> shuttles = shuttleRepository.findAll();
        List<Map<String, Object>> results = new ArrayList<>();

        for (Shuttle shuttle : shuttles) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", shuttle.getId());
            data.put("name", shuttle.getName());
            data.put("status", shuttle.getStatus());
            data.put("latitude", shuttle.getLatitude());
            data.put("longitude", shuttle.getLongitude());
            data.put("route", shuttle.getRoute() != null ? shuttle.getRoute().getName() : "None");
            data.put("currentStop", shuttle.getCurrentStop() != null ? shuttle.getCurrentStop().getName() : "None");
            results.add(data);
        }

        return results;
    }

    public void autoAllocateIdleShuttles() {
        Stop highDemandStop = getMostDemandedStop(); // from StopUsageService or ShuttleService
        if (highDemandStop == null) return;

        List<Shuttle> idleShuttles = shuttleRepository.findByStatus(ShuttleStatus.IDLE);

        for (Shuttle shuttle : idleShuttles) {
            shuttle.setCurrentStop(highDemandStop);
            shuttle.setStatus(ShuttleStatus.ON_ROUTE);
            shuttleRepository.save(shuttle);
        }
    }

    private Stop getMostDemandedStop() {
        List<Object[]> rawStats = stopUsageRepository.findStopUsageCounts();
        if (!rawStats.isEmpty()) {
            Long stopId = (Long) rawStats.get(0)[0];
            return stopRepository.findById(stopId).orElse(null);
        }
        return null;
    }

}