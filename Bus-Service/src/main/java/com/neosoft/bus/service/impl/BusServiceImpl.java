package com.neosoft.bus.service.impl;

import com.neosoft.bus.dto.*;
import com.neosoft.bus.entity.Bus;
import com.neosoft.bus.entity.BusStop;
import com.neosoft.bus.enums.BusSeatStatus;
import com.neosoft.bus.enums.BusStatus;
import com.neosoft.bus.exceptions.BusAlreadyPresentException;
import com.neosoft.bus.exceptions.BusNotFoundException;
import com.neosoft.bus.helper.Helper;
import com.neosoft.bus.repository.BusRepository;
import com.neosoft.bus.repository.BusRouteRepository;
import com.neosoft.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Override
    public BaseResponse addBus(BusRequest busRequest) {
            if (busRepository.findByBusName(busRequest.getBusName()) != null)
                throw new BusAlreadyPresentException("Bus name is already present.Please give another name.");
            Random random = new Random();
            Integer busNo  = random. nextInt(900) + 100;
            if (busRepository.findByBusNo(busNo) != null)
                throw new BusAlreadyPresentException("Bus no is already present.Please try again");
            Bus bus= Helper.convertDtoToEntity(busRequest);
            bus.setBusNo(busNo);
            bus=busRepository.save(bus);
            BusResponse busResponse=Helper.convertEntityToDto(bus);
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Bus Details added successfully")
                    .data(busResponse).build();
    }

    @Override
    public BaseResponse getBusByBusId(UUID busId) {
            Bus bus=busRepository.findByBusId(busId);
            if (Objects.isNull(bus))
                throw new BusNotFoundException("Bus Not Found");
            BusResponse busResponse=Helper.convertEntityToDto(bus);
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Bus Details Fetched successfully")
                    .data(busResponse).build();
    }

    @Override
    public BaseResponse getAllBuses() {
        List<Bus> busList=busRepository.findAll();
        List<BusResponse> busResponseList=new ArrayList<>();
        for (Bus bus:busList){
            BusResponse busResponse=Helper.convertEntityToDto(bus);
            busResponseList.add(busResponse);
        }
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("All Buses Details Fetched Successfully")
                .data(busResponseList).build();
    }

    @Override
    public BaseResponse deleteBus(UUID busId) {
        Bus bus=busRepository.findByBusId(busId);
        if (Objects.isNull(bus))
            throw new BusNotFoundException("Bus Not Found");
        bus.setStatus(BusStatus.INACTIVE);
        busRepository.save(bus);
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Bus Details Deleted Successfully").build();
    }

    @Override
    public BaseResponse getStopsByBusId(UUID busId) {
        Bus bus=busRepository.findByBusId(busId);
        if (Objects.isNull(bus))
            throw new BusNotFoundException("Bus Not Found");
        List<BusStopResponse> haltStopResponseList=new ArrayList<>();
        for (BusStop busStop:bus.getHaltStops()){
            BusStopResponse haltStopResponse=BusStopResponse.builder()
                    .haltStop(busStop.getHaltStop())
                    .time(busStop.getTime()).build();
            haltStopResponseList.add(haltStopResponse);
        }
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Bus Stops Fetched Successfully")
                .data(haltStopResponseList).build();
    }

    @Override
    public BaseResponse checkSeatAvailability(UUID busId,int travellers) {
        Bus busDetails = busRepository.findByBusId(busId);
        if (Objects.isNull(busDetails))
            throw new BusNotFoundException("Bus Not Found");
        BusSeatStatus busSeatStatus=null;
        if (busDetails.getAvailableSeats() >= travellers)
            busSeatStatus=BusSeatStatus.AVAILABLE;
        else
            busSeatStatus=BusSeatStatus.UNAVAILABLE;
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Check Seat Availability Successfully")
                .data(busSeatStatus).build();
    }

    @Override
    public BaseResponse updateAvailableSeats(UUID busId, int travellers) {
        Bus busDetails = busRepository.findByBusId(busId);
        if (Objects.isNull(busDetails))
            throw new BusNotFoundException("Bus Not Found");
        busDetails.setAvailableSeats(busDetails.getAvailableSeats()-travellers);
        busRepository.save(busDetails);
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Update Available Seat Successfully")
                .build();
    }

    @Override
    public BaseResponse getBusesFromSourceAndDestination(String source, String destination, String dateTime) {

        // Get the day of the week
        LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ISO_DATE);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        List<GetBusesQueryResponse> busIdList= busRouteRepository.getBusesIdsFromSourceAndDestination(source,destination);
        if (Objects.isNull(busIdList))
            throw new BusNotFoundException("Bus Not Found");
        List<GetBusesResponse> getBusesResponseList=new ArrayList<>();
        for (GetBusesQueryResponse getBus:busIdList){
            Bus bus= busRepository.findByIdAndStatus(getBus.getBus_Id(), BusStatus.ACTIVE);
            if (!Objects.isNull(bus) && bus.getAvailableDays().contains(dayOfWeek)){
                GetBusesResponse getBusesResponse=GetBusesResponse.builder()
                        .busNo(bus.getBusNo())
                        .busName(bus.getBusName())
                        .routeFrom(bus.getRouteFrom())
                        .routeTo(bus.getRouteTo())
                        .arrivalTime(bus.getArrivalTime())
                        .departureTime(bus.getDepartureTime())
                        .availableSeats(bus.getAvailableSeats())
                        .distance(getBus.getDistance())
                        .fare(getBus.getFare()).build();
                getBusesResponseList.add(getBusesResponse);
            }
        }
        if (Objects.isNull(getBusesResponseList))
            throw new BusNotFoundException("Bus Not Found");
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Fetch Buses Details Successfully")
                .data(getBusesResponseList).build();
    }
}
