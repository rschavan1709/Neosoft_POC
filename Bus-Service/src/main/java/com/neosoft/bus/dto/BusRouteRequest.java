package com.neosoft.bus.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BusRouteRequest {
    private String routeFrom;
    private String routeTo;
    private double distance;
    private double fare;
}
