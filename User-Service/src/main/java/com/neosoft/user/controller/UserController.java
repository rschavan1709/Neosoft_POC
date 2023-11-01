package com.neosoft.user.controller;

import com.neosoft.user.dto.UserRequest;
import com.neosoft.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserRequest userRequest){
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

    @PatchMapping("/update/{userId}")
    public ResponseEntity updateUser(@RequestBody UserRequest userRequest,@PathVariable UUID userId){
        return ResponseEntity.ok(userService.updateUser(userRequest,userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
