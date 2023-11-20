package com.neosoft.ticket.external.service.impl;

import com.neosoft.ticket.dto.BaseResponse;
import com.neosoft.ticket.external.dto.LoggedInUserResponse;
import com.neosoft.ticket.external.exceptions.UserNotFoundException;
import com.neosoft.ticket.external.service.FeignClientUserService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignClientUserServiceImpl {
    @Autowired
    private FeignClientUserService feignClientUserService;

    public LoggedInUserResponse getLoggedInUser() throws Exception {
        try{
            BaseResponse baseResponse = (BaseResponse) feignClientUserService.getLoggedInUser().getBody();
            return (LoggedInUserResponse) baseResponse.getData();
        }catch (FeignException ex){
             throw new UserNotFoundException("User Not Found");
        }
    }

}
