package com.neosoft.ticket.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TicketRequest {
    private String busName;
    private UUID busId;
    private String source;
    private String destination;
    private LocalTime sourceTime;
    private LocalTime destinationTime;
    private int travellers;
    private LocalDate journeyDate;
    private double distance;
    private double fare;
    private List<PassengerRequest> passengers;
}
