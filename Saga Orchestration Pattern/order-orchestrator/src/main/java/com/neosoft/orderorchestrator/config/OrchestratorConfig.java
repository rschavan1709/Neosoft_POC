package com.neosoft.orderorchestrator.config;

import com.neosoft.orderorchestrator.dto.OrchestratorRequestDTO;
import com.neosoft.orderorchestrator.dto.OrchestratorResponseDTO;
import com.neosoft.orderorchestrator.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class OrchestratorConfig {

    @Autowired
    private OrchestratorService orchestratorService;

    public Function<Flux<OrchestratorRequestDTO>,Flux<OrchestratorResponseDTO>> processor(){
        return flux -> flux
                .flatMap(dto -> orchestratorService.orderProduct(dto))
                .doOnNext(dto -> System.out.println("Status: "+dto.getSatus()));
    }
}
