package com.neosoft.user.service;

import com.neosoft.user.dto.BaseResponse;
import com.neosoft.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {

    BaseResponse addUser(UserRequest userRequest);

    BaseResponse getUserByUserId(UUID userId);

    BaseResponse getAllUsers();

    BaseResponse updateUser(UserRequest userRequest,UUID userId);

    BaseResponse deleteUser(UUID userId);

    BaseResponse getUserByEmail(String email);
}
