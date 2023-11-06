package com.neosoft.bus.controller;

import com.neosoft.bus.dto.BusRequest;
import com.neosoft.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/add-bus")
    public ResponseEntity addBus(@RequestBody BusRequest busRequest){
       return ResponseEntity.ok(busService.addBus(busRequest)) ;
    }

    @GetMapping("/getBus/{busId}")
    public ResponseEntity getBusByBusId(@PathVariable UUID busId){
        return ResponseEntity.ok(busService.getBusByBusId(busId));
    }

    @GetMapping("/getAllBuses")
    public ResponseEntity getAllBuses(){
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @DeleteMapping("/delete/{busId}")
    public ResponseEntity deleteBus(@PathVariable UUID busId){
        return ResponseEntity.ok(busService.deleteBus(busId));
    }

    @GetMapping("/getStops/{busId}")
    public ResponseEntity getStopsByBusId(@PathVariable UUID busId){
        return ResponseEntity.ok(busService.getStopsByBusId(busId));
    }

    @GetMapping("/getBuses")
    public ResponseEntity getBusesFromSourceAndDestination(@RequestParam String source, @RequestParam String destination, @RequestParam String dateTime){
        return ResponseEntity.ok(busService.getBusesFromSourceAndDestination(source,destination,dateTime));
    }
}
