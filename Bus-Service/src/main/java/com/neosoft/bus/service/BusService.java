package com.neosoft.bus.service;

import com.neosoft.bus.dto.BaseResponse;
import com.neosoft.bus.dto.BusRequest;
import java.util.UUID;


public interface BusService {
    BaseResponse addBus(BusRequest busRequest);

    BaseResponse getBusByBusId(UUID busId);

    BaseResponse getAllBuses();

    BaseResponse deleteBus(UUID busId);

    BaseResponse getStopsByBusId(UUID busId);

    BaseResponse checkSeatAvailability(UUID busId, int travellers);

    BaseResponse updateAvailableSeats(UUID busId, int travellers);

    BaseResponse getBusesFromSourceAndDestination(String source, String destination, String dateTime);
}
