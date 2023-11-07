package com.neosoft.user.dto;

import com.neosoft.user.enums.UserRole;
import com.neosoft.user.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
    private String password;
    private UserRole role;
    private UserStatus status;
}
