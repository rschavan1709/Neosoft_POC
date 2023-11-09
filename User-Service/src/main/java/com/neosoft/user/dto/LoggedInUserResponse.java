package com.neosoft.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoggedInUserResponse {

    private UUID userId;
    private String mobileNo;
    private String email;

}
