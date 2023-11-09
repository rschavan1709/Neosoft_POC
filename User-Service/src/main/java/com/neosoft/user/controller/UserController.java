package com.neosoft.user.controller;

import com.neosoft.user.dto.UserRequest;
import com.neosoft.user.service.UserService;
import com.neosoft.user.utils.CommonUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity getUserByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@RequestBody @Valid UserRequest userRequest,@PathVariable UUID userId){
        return ResponseEntity.ok(userService.updateUser(userRequest,userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/getLogInUser")
    public ResponseEntity getLoggedInUser() throws Exception {
        return ResponseEntity.ok(userService.getLoggedInUser());
    }
}
