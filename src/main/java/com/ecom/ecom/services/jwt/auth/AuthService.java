package com.ecom.ecom.services.jwt.auth;


import com.ecom.ecom.dto.SignupRequest;
import com.ecom.ecom.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
