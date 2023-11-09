package com.neosoft.ticket.service.impl;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.dto.TicketRequest;
import com.neosoft.ticket.dto.TicketResponse;
import com.neosoft.ticket.entity.Ticket;
import com.neosoft.ticket.enums.TicketStatus;
import com.neosoft.ticket.exceptions.TicketNotFoundException;
import com.neosoft.ticket.external.dto.LoggedInUserResponse;
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
    private TicketRepository ticketRepository;

    @Override
    public BaseResponse bookTicket(TicketRequest ticketRequest) throws Exception {
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
        ticket.setStatus(TicketStatus.BOOKED);
        ticket = ticketRepository.save(ticket);
        TicketResponse ticketResponse=HelperUtil.convertTicketEntityToDto(ticket);
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Ticket Booked Successfully")
                .data(ticketResponse).build();
    }

    @Override
    public BaseResponse cancelTicket(int ticketNo) {
        return null;
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
