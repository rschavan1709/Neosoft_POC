package com.neosoft.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
}
