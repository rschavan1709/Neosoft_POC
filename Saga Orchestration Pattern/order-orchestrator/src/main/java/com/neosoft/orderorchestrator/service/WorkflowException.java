package com.neosoft.orderorchestrator.service;

public class WorkflowException extends RuntimeException{

    public WorkflowException(String message){
        super(message);
    }
}
