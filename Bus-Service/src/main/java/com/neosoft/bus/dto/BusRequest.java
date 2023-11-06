package com.neosoft.bus.dto;

import com.neosoft.bus.entity.BusStop;
import lombok.Builder;
import lombok.Data;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class BusRequest {
    private String busName;
    private String routeFrom;
    private String routeTo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private List<String> availableDays;
    private List<BusStopRequest> haltStops;
    private List<BusRouteRequest> busRoutes;
}