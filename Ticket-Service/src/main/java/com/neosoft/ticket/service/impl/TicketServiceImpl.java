package com.neosoft.ticket.service.impl;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.dto.TicketRequest;
import com.neosoft.ticket.entity.Ticket;
import com.neosoft.ticket.helper.HelperUtil;
import com.neosoft.ticket.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public BaseResponse bookTicket(TicketRequest ticketRequest) {
        Random random = new Random();
        Integer ticketNo  = random. nextInt(9000) + 1000;
        Ticket ticket= HelperUtil.convertTicketDtoToEntity(ticketRequest);
        ticket.setTicketNo(ticketNo);
        ticket.setTicketId(UUID.randomUUID());
        
        return null;
    }

    @Override
    public BaseResponse cancelTicket(int ticketNo) {
        return null;
    }

    @Override
    public BaseResponse getTicketByTicketNo(int ticketNo) {
        return null;
    }
}
