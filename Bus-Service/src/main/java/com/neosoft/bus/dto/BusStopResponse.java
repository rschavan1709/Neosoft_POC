package com.neosoft.bus.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class BusStopResponse {
    private String haltStop;
    private LocalTime time;
}
