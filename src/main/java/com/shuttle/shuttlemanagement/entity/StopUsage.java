package com.shuttle.shuttlemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;
    private LocalDateTime timestamp;
    @ManyToOne
    private User user;

    @ManyToOne
    private Stop stop;

    private String userEmail; // Optional
}