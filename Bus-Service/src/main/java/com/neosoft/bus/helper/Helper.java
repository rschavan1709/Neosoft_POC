package com.neosoft.bus.helper;

import com.neosoft.bus.dto.*;
import com.neosoft.bus.entity.Bus;
import com.neosoft.bus.entity.BusRoute;
import com.neosoft.bus.entity.BusStop;
import com.neosoft.bus.enums.BusStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Helper {

    public static Bus convertDtoToEntity(BusRequest busRequest){
        List<BusRoute> busRoutes=new ArrayList<>();
        for (BusRouteRequest busRouteRequest:busRequest.getBusRoutes()){
            BusRoute busRoute=BusRoute.builder()
                    .routeId(UUID.randomUUID())
                    .routeFrom(busRouteRequest.getRouteFrom())
                    .routeTo(busRouteRequest.getRouteTo())
                    .distance(busRouteRequest.getDistance())
                    .fare(busRouteRequest.getFare()).build();
            busRoutes.add(busRoute);
        }
        List<BusStop> busStops=new ArrayList<>();
        for (BusStopRequest busStopRequest :busRequest.getHaltStops()){
            BusStop busStop=BusStop.builder()
                    .haltStop(busStopRequest.getHaltStop())
                    .time(busStopRequest.getTime()).build();
            busStops.add(busStop);
        }
        Bus bus= Bus.builder()
                .busId(UUID.randomUUID())
                .busName(busRequest.getBusName())
                .routeFrom(busRequest.getRouteFrom())
                .routeTo(busRequest.getRouteTo())
                .arrivalTime(busRequest.getArrivalTime())
                .departureTime(busRequest.getDepartureTime())
                .totalSeats(busRequest.getTotalSeats())
                .availableSeats(busRequest.getTotalSeats())
                .availableDays(busRequest.getAvailableDays())
                .haltStops(busStops)
                .busRoutes(busRoutes)
                .status(BusStatus.ACTIVE).build();
        return bus;
    }

    public static BusResponse convertEntityToDto(Bus bus){
        List<BusRouteResponse> busRoutes=new ArrayList<>();
        for (BusRoute busRoute:bus.getBusRoutes()){
            BusRouteResponse busRouteResponse=BusRouteResponse.builder()
                    .routeId(busRoute.getRouteId())
                    .routeFrom(busRoute.getRouteFrom())
                    .routeTo(busRoute.getRouteTo())
                    .distance(busRoute.getDistance())
                    .fare(busRoute.getFare()).build();
            busRoutes.add(busRouteResponse);
        }
        List<BusStopResponse> busStops=new ArrayList<>();
        for (BusStop busStop:bus.getHaltStops()){
            BusStopResponse busStopResponse=BusStopResponse.builder()
                    .haltStop(busStop.getHaltStop())
                    .time(busStop.getTime()).build();
            busStops.add(busStopResponse);
        }
        BusResponse busResponse=BusResponse.builder()
                .busId(bus.getBusId())
                .busNo(bus.getBusNo())
                .busName(bus.getBusName())
                .routeFrom(bus.getRouteFrom())
                .routeTo(bus.getRouteTo())
                .arrivalTime(bus.getArrivalTime())
                .departureTime(bus.getDepartureTime())
                .totalSeats(bus.getTotalSeats())
                .availableSeats(bus.getAvailableSeats())
                .availableDays(bus.getAvailableDays())
                .haltStops(busStops)
                .busRoutes(busRoutes)
                .status(bus.getStatus()).build();
        return busResponse;
    }
}