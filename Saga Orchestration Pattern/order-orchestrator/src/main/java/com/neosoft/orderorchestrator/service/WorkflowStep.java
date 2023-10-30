package com.neosoft.orderorchestrator.service;

import com.neosoft.orderorchestrator.enums.WorkflowStepStatus;
import reactor.core.publisher.Mono;

public interface WorkflowStep {
    WorkflowStepStatus getStatus();
    Mono<Boolean> process();
    Mono<Boolean> revert();
}
