package com.neosoft.bus.dto;

import com.neosoft.bus.entity.BusRoute;
import com.neosoft.bus.entity.BusStop;
import com.neosoft.bus.enums.BusStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class BusResponse {
    private UUID busId;
    private Integer busNo;
    private String busName;
    private String routeFrom;
    private String routeTo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private List<String> availableDays;
    private List<BusStopResponse> haltStops;
    private List<BusRouteResponse> busRoutes;
    private BusStatus status;
}
