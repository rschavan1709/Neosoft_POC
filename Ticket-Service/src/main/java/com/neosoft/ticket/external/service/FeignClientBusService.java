package com.neosoft.ticket.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "Bus-Service",url = "http://localhost:8082/api/v1/bus")
public interface FeignClientBusService {

    @GetMapping("/check-seat")
    public ResponseEntity checkSeatAvailability(@PathVariable UUID busId, @PathVariable int travellers);

    @GetMapping("/update-seat")
    public ResponseEntity updateAvailableSeats(@PathVariable UUID busId,@PathVariable int travellers);
}
