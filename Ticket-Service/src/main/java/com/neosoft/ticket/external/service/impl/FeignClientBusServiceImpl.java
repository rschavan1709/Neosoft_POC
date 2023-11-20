package com.neosoft.ticket.external.service.impl;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.external.exceptions.BusNotFoundException;
import com.neosoft.ticket.external.enums.BusSeatStatus;
import com.neosoft.ticket.external.service.FeignClientBusService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeignClientBusServiceImpl {
    @Autowired
    private FeignClientBusService feignClientBusService;

    public BusSeatStatus checkSeatAvailability(UUID busId,int travellers){
        try{
            BaseResponse baseResponse = (BaseResponse) feignClientBusService.checkSeatAvailability(busId,travellers).getBody();
            return (BusSeatStatus) baseResponse.getData();
        }catch (FeignException ex){
            throw new BusNotFoundException("Bus Not Found");
        }
    }

    public void updateAvailableSeats(UUID busId,int travellers) throws Exception {
        try{
            feignClientBusService.updateAvailableSeats(busId,travellers);
        }catch (FeignException ex){
            throw new Exception("Updation of available seats failed");
        }
    }
}
