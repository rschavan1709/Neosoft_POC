package com.neosoft.ticket.service;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.dto.TicketRequest;

public interface TicketService {
    BaseResponse bookTicket(TicketRequest ticketRequest) throws Exception;

    BaseResponse cancelTicket(int ticketNo);

    BaseResponse getTicketByTicketNo(int ticketNo);
}
