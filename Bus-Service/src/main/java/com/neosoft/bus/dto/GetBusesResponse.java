package com.neosoft.bus.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class GetBusesResponse {
    private Integer busNo;
    private String busName;
    private String routeFrom;
    private String routeTo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private double distance;
    private double fare;
    private Integer availableSeats;
}
