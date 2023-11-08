package com.neosoft.ticket.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PassengerResponse {
    private UUID passengerId;
    private String passengerName;
    private int age;
    private String gender;
}
