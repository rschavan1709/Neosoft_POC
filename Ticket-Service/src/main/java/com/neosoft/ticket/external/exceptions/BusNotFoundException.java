package com.neosoft.ticket.external.exceptions;

public class BusNotFoundException extends RuntimeException{

    public BusNotFoundException(String message) {
        super(message);
    }
}