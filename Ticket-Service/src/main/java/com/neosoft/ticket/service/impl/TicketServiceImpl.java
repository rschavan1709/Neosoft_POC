package com.neosoft.ticket.service.impl;

import com.neosoft.ticket.dto.*;
import com.neosoft.ticket.entity.Ticket;
import com.neosoft.ticket.enums.TicketStatus;
import com.neosoft.ticket.exceptions.SeatNotAvailableException;
import com.neosoft.ticket.exceptions.TicketNotFoundException;
import com.neosoft.ticket.external.dto.LoggedInUserResponse;
import com.neosoft.ticket.external.enums.BusSeatStatus;
import com.neosoft.ticket.external.service.impl.FeignClientBusServiceImpl;
import com.neosoft.ticket.external.service.impl.FeignClientUserServiceImpl;
import com.neosoft.ticket.helper.HelperUtil;
import com.neosoft.ticket.repository.TicketRepository;
import com.neosoft.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private FeignClientUserServiceImpl feignClientUserService;
    @Autowired
    private FeignClientBusServiceImpl feignClientBusService;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public BaseResponse createTicket(TicketRequest ticketRequest) throws Exception {
        BusSeatStatus status=feignClientBusService.checkSeatAvailability(ticketRequest.getBusId(),ticketRequest.getTravellers());
        if (status.equals(BusSeatStatus.UNAVAILABLE))
            throw new SeatNotAvailableException("Seats not available");
        Random random = new Random();
        Integer ticketNo  = random. nextInt(90000) + 10000;
        Ticket ticket= HelperUtil.convertTicketDtoToEntity(ticketRequest);
        ticket.setTicketNo(ticketNo);
        ticket.setTicketId(UUID.randomUUID());
        LoggedInUserResponse userResponse = feignClientUserService.getLoggedInUser();
        ticket.setUserId(userResponse.getUserId());
        ticket.setEmailId(userResponse.getEmail());
        ticket.setMobileNo(userResponse.getMobileNo());
        ticket.setBookTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CREATED);
        ticket = ticketRepository.save(ticket);
        TicketResponse ticketResponse=HelperUtil.convertTicketEntityToDto(ticket);
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Ticket Created Successfully")
                .data(ticketResponse).build();
    }

    @Override
    public BaseResponse cancelTicket(CancelTicketRequest cancelTicketRequest) {
        Ticket ticket = ticketRepository.findByTicketId(cancelTicketRequest.getTicketId());
        if (Objects.isNull(ticket))
            throw new TicketNotFoundException("Ticket Not Found");
        //implement later for refund amount
        double refundAmount=0;
        ticket.setRefundAmount(refundAmount);
        ticket.setCancelTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CANCELLED);
        ticket=ticketRepository.save(ticket);
        CancelTicketResponse cancelTicketResponse=CancelTicketResponse.builder()
                .ticketId(ticket.getTicketId())
                .status(ticket.getStatus()).build();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Ticket Cancelled Successfully")
                .data(cancelTicketResponse).build();
    }

    @Override
    public BaseResponse getTicketByTicketNo(int ticketNo) {
        Ticket ticket =  ticketRepository.findByTicketNo(ticketNo);
        if (Objects.isNull(ticket))
            throw new TicketNotFoundException("Ticket Not Found");
        TicketResponse ticketResponse=HelperUtil.convertTicketEntityToDto(ticket);
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Ticket Details Fetched Successfully")
                .data(ticketResponse).build();
    }
}
