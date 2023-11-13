package com.neosoft.ticket.dto;

import com.neosoft.ticket.enums.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CancelTicketRequest {

    private UUID ticketId;

}
