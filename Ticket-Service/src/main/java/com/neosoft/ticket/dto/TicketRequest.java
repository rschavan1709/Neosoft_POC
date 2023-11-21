package com.neosoft.ticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

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
    @Range(min = 1,message = "travellers should be atleast 1")
    private int travellers;
    private LocalDate journeyDate;
    private double distance;
    private double fare;
    @Email(message = "Email Id is not valid")
    @NotBlank(message = "Email Id is mandatory")
    private String emailId;
    @NotBlank(message = "Mobile No is mandatory")
    @Pattern(regexp = "\\+\\d{1,3}?[7-9][0-9]{9}",message = "Mobile No is not valid.It must have 10 digits and start with country code")
    private String mobileNo;
    private UUID userId;
    private List<PassengerRequest> passengers;
}
