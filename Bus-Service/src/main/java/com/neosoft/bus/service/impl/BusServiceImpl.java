package com.neosoft.bus.service.impl;

import com.neosoft.bus.dto.BaseResponse;
import com.neosoft.bus.dto.BusRequest;
import com.neosoft.bus.dto.BusResponse;
import com.neosoft.bus.entity.Bus;
import com.neosoft.bus.enums.BusStatus;
import com.neosoft.bus.exceptions.BusAlreadyPresentException;
import com.neosoft.bus.exceptions.BusNotFoundException;
import com.neosoft.bus.helper.Helper;
import com.neosoft.bus.repository.BusRepository;
import com.neosoft.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

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
    public BaseResponse getStopsByBusNo(Integer busNo) {
        Bus bus=busRepository.findByBusNo(busNo);
        if (Objects.isNull(bus))
            throw new BusNotFoundException("Bus Not Found");
        List<String> haltStops=bus.getHaltStops();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Bus Stops Fetched Successfully")
                .data(haltStops).build();
    }
}
