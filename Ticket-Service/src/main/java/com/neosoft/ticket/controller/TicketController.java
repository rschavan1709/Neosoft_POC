package com.neosoft.ticket.controller;

import com.neosoft.ticket.dto.CancelTicketRequest;
import com.neosoft.ticket.dto.TicketRequest;
import com.neosoft.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity createTicket(@RequestBody TicketRequest ticketRequest) throws Exception {
        return ResponseEntity.ok(ticketService.createTicket(ticketRequest));
    }

    @GetMapping("/get-ticket/{ticketNo}")
    public ResponseEntity getTicketByTicketNo(@PathVariable int ticketNo){
        return ResponseEntity.ok(ticketService.getTicketByTicketNo(ticketNo));
    }

    @PostMapping("/cancel-ticket")
    public ResponseEntity cancelTicket(@RequestBody CancelTicketRequest cancelTicketRequest){
        return ResponseEntity.ok(ticketService.cancelTicket(cancelTicketRequest));
    }
}
