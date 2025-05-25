package com.shuttle.shuttlemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private City fromCity;

    @ManyToOne
    private City toCity;

    @ManyToOne
    private Shuttle shuttle;

    private LocalDateTime startTime;

    private int fare;
}