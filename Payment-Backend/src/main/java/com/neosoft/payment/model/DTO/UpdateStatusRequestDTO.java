package com.neosoft.payment.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusRequestDTO {
    private String orderId;
    private String paymentId;
    private String status;
}
