package com.neosoft.user.service;

import com.neosoft.user.dto.AuthenticationRequest;
import com.neosoft.user.dto.BaseResponse;

public interface AuthenticationService {
    BaseResponse authenticate(AuthenticationRequest request);
}
