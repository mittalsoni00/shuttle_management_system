package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.dto.RouteRequest;
import com.shuttle.shuttlemanagement.dto.StopRequest;
import com.shuttle.shuttlemanagement.entity.Route;
import com.shuttle.shuttlemanagement.entity.Stop;
import com.shuttle.shuttlemanagement.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public Route createRoute(RouteRequest request) {
        List<Stop> stops = new ArrayList<>();
        Route route = Route.builder()
                .name(request.getName())
                .stops(stops)
                .build();

        for (StopRequest stopReq : request.getStops()) {
            Stop stop = Stop.builder()
                    .name(stopReq.getName())
                    .stopOrder(stopReq.getStopOrder())
                    .route(route)
                    .build();
            stops.add(stop);
        }

        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public void deleteRoute(Long routeId) {
        routeRepository.deleteById(routeId);
    }
}
