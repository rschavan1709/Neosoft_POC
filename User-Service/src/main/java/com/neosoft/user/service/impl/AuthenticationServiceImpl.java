package com.neosoft.user.service.impl;

import com.neosoft.user.config.JwtService;
import com.neosoft.user.dto.AuthenticationRequest;
import com.neosoft.user.dto.AuthenticationResponse;
import com.neosoft.user.dto.BaseResponse;
import com.neosoft.user.entity.User;
import com.neosoft.user.repository.UserRepository;
import com.neosoft.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public BaseResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken=jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder().token(jwtToken).build();
        return BaseResponse.builder()
                .code(HttpStatus.OK.value())
                .message("User Authenticated Successfully")
                .data(authenticationResponse).build();
    }
}
