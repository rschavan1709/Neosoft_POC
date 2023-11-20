package com.neosoft.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class UserRequest {
    @NotBlank(message = "First Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]{2,20}$",message = "First Name should have min 2 characters")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]{2,20}$",message = "Last Name should have min 2 characters")
    private String lastName;
    @Range(min = 1,message = "Age is mandatory and it should be greater than 1")
    private Integer age;
    @NotBlank(message = "Mobile No is mandatory")
    @Pattern(regexp = "\\+\\d{1,3}?[7-9][0-9]{9}",message = "Mobile No is not valid.It must have 10 digits and start with country code")
    private String mobileNo;
    @Email(message = "Email Id is not valid")
    @NotBlank(message = "Email Id is mandatory")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,15}$",message = "Password should be at least 8 characters and at most 15 characters. password have at least 1 uppercase character, 1 lowercase character, 1 special character and 1 digit")
    private String password;
}
