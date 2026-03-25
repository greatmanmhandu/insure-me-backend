package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.User;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.JwtTokenRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.response.JwtTokenResponse;
import com.vozhe.jwt.dashboard_and_mobile.payload.response.UserDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface AccountCreationService {
    ResponseEntity<JwtTokenResponse> createToken(JwtTokenRequest authenticationRequest) throws Exception;
    ResponseEntity<UserDTO> getUserInfo(Principal user);
    ResponseEntity<JwtTokenResponse> createTokenMobileApp(JwtTokenRequest authenticationRequest) throws Exception;

    User getUserByUsername(String username);

//    ResponseEntity<JwtTokenResponse> updatePasswordForMobileApp(JwtTokenRequest authenticationRequest) throws Exception;
}
