package com.neosoft.orderorchestrator.steps;

import com.neosoft.orderorchestrator.dto.PaymentRequestDTO;
import com.neosoft.orderorchestrator.dto.PaymentResponseDTO;
import com.neosoft.orderorchestrator.enums.PaymentStatus;
import com.neosoft.orderorchestrator.enums.WorkflowStepStatus;
import com.neosoft.orderorchestrator.service.WorkflowStep;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PaymentStep implements WorkflowStep {
    private final WebClient webClient;
    private final PaymentRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(WebClient webClient, PaymentRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                .post()
                .uri("/payment/debit")
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class)
                .map(r -> r.getStatus().equals(PaymentStatus.PAYMENT_APPROVED))
                .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/payment/credit")
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }
}
