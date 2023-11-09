package com.neosoft.ticket.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "User-Service",url = "http://localhost:8081/api/v1/user")
public interface FeignClientUserService {

    @GetMapping("/getLogInUser")
    public ResponseEntity getLoggedInUser() throws Exception;

}
