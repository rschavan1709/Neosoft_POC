package com.neosoft.bus.service.impl;

import com.neosoft.bus.dto.BaseResponse;
import com.neosoft.bus.dto.BusRouteRequest;
import com.neosoft.bus.dto.BusRouteResponse;
import com.neosoft.bus.entity.BusRoute;
import com.neosoft.bus.exceptions.BusAlreadyPresentException;
import com.neosoft.bus.repository.BusRouteRepository;
import com.neosoft.bus.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Override
    public BaseResponse addBusRoute(BusRouteRequest busRouteRequest) {
        BusRoute busRoute = busRouteRepository.findByRouteFromAndRouteTo(busRouteRequest.getRouteFrom(),busRouteRequest.getRouteTo());
        if (!Objects.isNull(busRoute)){
//            if (busRoute.getBusId().containsAll(busRouteRequest.getBusId())){
//                throw new BusAlreadyPresentException("Bus Already exists for this Bus Route");
//            }
//            busRoute.getBusId().addAll(busRouteRequest.getBusId());
        }
        else {
            busRoute.setRouteId(UUID.randomUUID());
            busRoute.setRouteFrom(busRouteRequest.getRouteFrom());
            busRoute.setRouteTo(busRouteRequest.getRouteTo());
            busRoute.setDistance(busRouteRequest.getDistance());
            busRoute.setFare(busRouteRequest.getFare());
          //  busRoute.setBusId(busRouteRequest.getBusId());
        }
        busRoute = busRouteRepository.save(busRoute);
        BusRouteResponse busRouteResponse=BusRouteResponse.builder()
                .routeId(busRoute.getRouteId())
                .routeFrom(busRoute.getRouteFrom())
                .routeTo(busRoute.getRouteTo())
                .distance(busRoute.getDistance())
                .fare(busRoute.getFare())
                .build();
              //  .busId(busRoute.getBusId()).build();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Bus Route Details Added Successfully")
                .data(busRouteResponse).build();
    }

    @Override
    public BaseResponse getBusesFromSourceAndDestination(String source, String destination) {
        return null;
    }
}
