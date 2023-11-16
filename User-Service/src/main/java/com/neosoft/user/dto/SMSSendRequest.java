package com.neosoft.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSSendRequest {
    private String destinationSMSNumber;
    private String smsMessages;
}
