package com.shuttle.shuttlemanagement.dto;

import lombok.Data;

@Data
public class ShuttleStatusUpdateRequest {
    private Long shuttleId;
    private String status2;   //this will autogenerate getstatus and setstatus by lombok
    private Long stopId; // optional
}
