package com.shuttle.shuttlemanagement.service;



import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FareService {
    public double calculateFare(LocalDateTime time) {
        int hour = time.getHour();
        if ((hour >= 8 && hour <= 10) || (hour >= 17 && hour <= 19)) {
            return 20.0; // Peak fare
        }
        return 10.0; // Off-peak fare
    }
}