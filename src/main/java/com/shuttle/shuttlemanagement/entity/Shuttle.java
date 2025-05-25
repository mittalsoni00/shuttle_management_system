package com.shuttle.shuttlemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shuttles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shuttle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shuttleNumber;

    private int capacity;
    private String name;

    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private ShuttleStatus status;

    @ManyToOne
    private Route route;
    private String status2;
    @ManyToOne
    private Stop currentStop;
}