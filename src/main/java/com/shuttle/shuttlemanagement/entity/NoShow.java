package com.shuttle.shuttlemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class NoShow {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    private Stop stop;

    @ManyToOne
    private Trip trip;

    private LocalDateTime timestamp;
    private String reason;
}