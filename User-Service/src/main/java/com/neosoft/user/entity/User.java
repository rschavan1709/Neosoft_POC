package com.neosoft.user.entity;

import com.neosoft.user.enums.UserRole;
import com.neosoft.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID userId;
    private String firstName;
    private String lastName;
    private int age;
    private String mobileNo;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
