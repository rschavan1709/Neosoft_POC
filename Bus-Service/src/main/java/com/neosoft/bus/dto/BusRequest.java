package com.neosoft.bus.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BusRequest {
    private String busName;
    private String routeFrom;
    private String routeTo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private List<String> haltStops;
    private List<BusRouteRequest> busRoutes;
}
