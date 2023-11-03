package com.neosoft.bus.service;

import com.neosoft.bus.dto.BaseResponse;
import com.neosoft.bus.dto.BusRouteRequest;

public interface BusRouteService {
    BaseResponse addBusRoute(BusRouteRequest busRouteRequest);

    BaseResponse getBusesFromSourceAndDestination(String source,String destination);
}
