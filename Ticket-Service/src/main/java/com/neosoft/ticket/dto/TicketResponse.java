package com.neosoft.ticket.dto;

import com.neosoft.ticket.enums.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TicketResponse {
    private UUID ticketId;
    private int ticketNo;
    private String emailId;
    private String mobileNo;
    private int travellers;
    private String busName;
    private UUID busId;
    private String source;
    private String destination;
    private LocalTime sourceTime;
    private LocalTime destinationTime;
    private LocalDate journeyDate;
    private double distance;
    private double ticketFare;
    private double bookAmount;
    private LocalDateTime bookTime;
    private TicketStatus status;
    private List<PassengerResponse> passengers;
}
