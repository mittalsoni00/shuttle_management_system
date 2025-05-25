package com.shuttle.shuttlemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StopRequest {
    @NotBlank(message = "Stop name is required")
    private String name;
    @NotNull(message = "Stop order is required")
    private int stopOrder;
}