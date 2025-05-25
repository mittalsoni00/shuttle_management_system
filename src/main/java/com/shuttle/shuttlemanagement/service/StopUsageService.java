package com.shuttle.shuttlemanagement.service;

import com.shuttle.shuttlemanagement.dto.StopUsageResponse;
import com.shuttle.shuttlemanagement.entity.Stop;
import com.shuttle.shuttlemanagement.entity.StopUsage;
import com.shuttle.shuttlemanagement.entity.User;
import com.shuttle.shuttlemanagement.repository.StopRepository;
import com.shuttle.shuttlemanagement.repository.StopUsageRepository;
import com.shuttle.shuttlemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopUsageService {
    private final StopUsageRepository stopUsageRepository;
    private final StopRepository stopRepository;
    private final UserRepository userRepository;

    public void logUsage(Long userId, Long stopId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Stop> stop = stopRepository.findById(stopId);

        if (user.isPresent() && stop.isPresent()) {
            StopUsage usage = StopUsage.builder()
                    .user(user.get())
                    .stop(stop.get())
                    .timestamp(LocalDateTime.now())
                    .build();
            stopUsageRepository.save(usage);
        } else {
            throw new RuntimeException("Invalid user or stop");
        }
    }
    public List<StopUsageResponse> getAllUsageLogs() {
        List<StopUsage> usages = stopUsageRepository.findAll();
        return usages.stream()
                .map(u -> new StopUsageResponse(
                        u.getUser().getEmail(),
                        u.getStop().getName(),
                        u.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getUsageStats() {
        List<Object[]> stats = stopUsageRepository.getStopUsageStats();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : stats) {
            Map<String, Object> data = new HashMap<>();
            data.put("stop", row[0]);
            data.put("count", row[1]);
            result.add(data);
        }

        return result;
    }
    public Stop getMostDemandedStop() {
        List<Object[]> rawStats = stopUsageRepository.findStopUsageCounts();
        if (!rawStats.isEmpty()) {
            Long stopId = (Long) rawStats.get(0)[0];
            return stopRepository.findById(stopId).orElse(null);
        }
        return null;
    }
}
