package com.shuttle.shuttlemanagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

//package com.shuttle.shuttlemanagement.dto;

import lombok.Getter;
import lombok.Setter;

//import java.util.List;

@Getter
@Setter
public class RouteRequest {
    @NotBlank(message = "Route name is required")
    private String name;
    @Valid
    private List<StopRequest> stops;
}
