package com.neosoft.user.service.impl;

import com.neosoft.user.dto.BaseResponse;
import com.neosoft.user.dto.UserRequest;
import com.neosoft.user.dto.UserResponse;
import com.neosoft.user.entity.User;
import com.neosoft.user.enums.UserRole;
import com.neosoft.user.enums.UserStatus;
import com.neosoft.user.exceptions.UserAlreadyPresentException;
import com.neosoft.user.exceptions.UserNotFoundException;
import com.neosoft.user.repository.UserRepository;
import com.neosoft.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse addUser(UserRequest userRequest) {
             Optional<User> userExists=userRepository.findByEmail(userRequest.getEmail());
             if (userExists.isPresent())
                 throw new UserAlreadyPresentException("User with this email already exists");
             User user = User.builder()
                     .userId(UUID.randomUUID())
                     .firstName(userRequest.getFirstName())
                     .lastName(userRequest.getLastName())
                     .age(userRequest.getAge())
                     .mobileNo(userRequest.getMobileNo())
                     .email(userRequest.getEmail())
                     .password(passwordEncoder.encode(userRequest.getPassword()))
                     .role(UserRole.USER)
                     .status(UserStatus.ACTIVE).build();
            user=userRepository.save(user);
            UserResponse userResponse=UserResponse.builder()
                    .userId(user.getUserId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .age(user.getAge())
                    .mobileNo(user.getMobileNo())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .status(user.getStatus()).build();
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("User Registered Successfully")
                    .data(userResponse).build();
    }

    @Override
    public BaseResponse getUserByUserId(UUID userId) {
            User user=userRepository.findByUserId(userId);
            if (Objects.isNull(user)){
                throw new UserNotFoundException("User Not Found");
            }
            UserResponse userResponse=UserResponse.builder()
                    .userId(user.getUserId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .age(user.getAge())
                    .mobileNo(user.getMobileNo())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .status(user.getStatus()).build();
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("User Details Fetched Successfully")
                    .data(userResponse).build();
    }

    @Override
    public BaseResponse getAllUsers() {
            List<User> userList = userRepository.findAll();
            List<UserResponse> userResponseList=new ArrayList<>();
            for (User user:userList){
                UserResponse userResponse=UserResponse.builder()
                        .userId(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .age(user.getAge())
                        .mobileNo(user.getMobileNo())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .status(user.getStatus()).build();
                userResponseList.add(userResponse);
            }
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("All Users Details Fetched Successfully")
                    .data(userResponseList).build();
    }

    @Override
    public BaseResponse updateUser(UserRequest userRequest, UUID userId) {
            User user=userRepository.findByUserId(userId);
            if (Objects.isNull(user)){
                throw new UserNotFoundException("User Not Found");
            }
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setAge(userRequest.getAge());
            user.setMobileNo(userRequest.getMobileNo());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user=userRepository.save(user);
            UserResponse userResponse=UserResponse.builder()
                    .userId(user.getUserId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .age(user.getAge())
                    .mobileNo(user.getMobileNo())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .status(user.getStatus()).build();

            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("User Details Updated Successfully")
                    .data(userResponse).build();
    }

    @Override
    public BaseResponse deleteUser(UUID userId) {
            User user=userRepository.findByUserId(userId);
            if (Objects.isNull(user)){
                throw new UserNotFoundException("User Not Found");
            }
            user.setStatus(UserStatus.INACTIVE);
            userRepository.save(user);
            return BaseResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("User Details Deleted Successfully").build();
    }

    @Override
    public BaseResponse getUserByEmail(String email) {
        User user=userRepository.findByEmail(email).get();
        if (Objects.isNull(user)){
            throw new UserNotFoundException("User Not Found");
        }
        UserResponse userResponse=UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus()).build();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("User Details Fetched Successfully")
                .data(userResponse).build();
    }

}
