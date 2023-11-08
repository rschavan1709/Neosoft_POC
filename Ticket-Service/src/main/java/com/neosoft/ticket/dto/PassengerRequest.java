package com.neosoft.ticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerRequest {
    private String passengerName;
    private int age;
    private String gender;
}
