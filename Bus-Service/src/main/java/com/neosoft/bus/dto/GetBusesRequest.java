package com.neosoft.bus.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetBusesRequest {
    private String source;
    private String destination;
    private LocalDateTime dateTime;
}
