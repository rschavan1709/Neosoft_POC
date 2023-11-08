package com.neosoft.ticket.helper;

import com.neosoft.ticket.dto.PassengerRequest;
import com.neosoft.ticket.dto.PassengerResponse;
import com.neosoft.ticket.dto.TicketRequest;
import com.neosoft.ticket.dto.TicketResponse;
import com.neosoft.ticket.entity.Passenger;
import com.neosoft.ticket.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class HelperUtil {

    public static Ticket convertTicketDtoToEntity(TicketRequest ticketRequest){
        Ticket ticket= Ticket.builder()
                .busName(ticketRequest.getBusName())
                .busId(ticketRequest.getBusId())
                .source(ticketRequest.getSource())
                .destination(ticketRequest.getDestination())
                .sourceTime(ticketRequest.getSourceTime())
                .destinationTime(ticketRequest.getDestinationTime())
                .travellers(ticketRequest.getTravellers())
                .journeyDate(ticketRequest.getJourneyDate())
                .distance(ticketRequest.getDistance())
                .ticketFare(ticketRequest.getFare())
                .passengers(convertPassengerListDtoToEntity(ticketRequest.getPassengers())).build();
        return ticket;
    }

    public static List<Passenger> convertPassengerListDtoToEntity(List<PassengerRequest> passengerRequestList){
        List<Passenger> passengerList=new ArrayList<>();
        for (PassengerRequest passengerRequest:passengerRequestList){
            Passenger passenger= Passenger.builder()
                    .passengerName(passengerRequest.getPassengerName())
                    .age(passengerRequest.getAge())
                    .gender(passengerRequest.getGender()).build();
            passengerList.add(passenger);
        }
        return passengerList;
    }

    public static TicketResponse convertTicketEntityToDto(Ticket ticket){
        TicketResponse ticketResponse= TicketResponse.builder()
                .ticketId(ticket.getTicketId())
                .ticketNo(ticket.getTicketNo())
                .emailId(ticket.getEmailId())
                .mobileNo(ticket.getMobileNo())
                .travellers(ticket.getTravellers())
                .busName(ticket.getBusName())
                .busId(ticket.getBusId())
                .source(ticket.getSource())
                .destination(ticket.getDestination())
                .sourceTime(ticket.getSourceTime())
                .destinationTime(ticket.getDestinationTime())
                .journeyDate(ticket.getJourneyDate())
                .distance(ticket.getDistance())
                .ticketFare(ticket.getTicketFare())
                .bookTime(ticket.getBookTime())
                .status(ticket.getStatus())
                .passengers(convertPassengerListEntityToDto(ticket.getPassengers())).build();
        return ticketResponse;
    }

    public static List<PassengerResponse> convertPassengerListEntityToDto(List<Passenger> passengerList){
        List<PassengerResponse> passengerResponseList=new ArrayList<>();
        for (Passenger passenger:passengerList){
            PassengerResponse passengerResponse=PassengerResponse.builder()
                    .passengerId(passenger.getPassengerId())
                    .passengerName(passenger.getPassengerName())
                    .age(passenger.getAge())
                    .gender(passenger.getGender()).build();
            passengerResponseList.add(passengerResponse);
        }
        return passengerResponseList;
    }
}
