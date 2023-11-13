package com.neosoft.ticket.service;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.dto.CancelTicketRequest;
import com.neosoft.ticket.dto.TicketRequest;

import java.util.UUID;

public interface TicketService {
    BaseResponse bookTicket(TicketRequest ticketRequest) throws Exception;

    BaseResponse cancelTicket(CancelTicketRequest cancelTicketRequest);

    BaseResponse getTicketByTicketNo(int ticketNo);
}
