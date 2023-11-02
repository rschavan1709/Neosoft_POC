package com.neosoft.bus.exceptions;

public class BusNotFoundException extends RuntimeException{

    public BusNotFoundException(String message) {
        super(message);
    }
}
