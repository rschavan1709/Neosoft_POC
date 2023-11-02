package com.neosoft.bus.service;

import com.neosoft.bus.dto.BaseResponse;
import com.neosoft.bus.dto.BusRequest;

import java.util.UUID;

public interface BusService {
    BaseResponse addBus(BusRequest busRequest);

    BaseResponse getBusByBusId(UUID busId);

    BaseResponse getAllBuses();

    BaseResponse deleteBus(UUID busId);

    BaseResponse getBusesBySourceAndDestination(String source,String destination);

    BaseResponse getStopsByBusNo(Integer busNo);
}
