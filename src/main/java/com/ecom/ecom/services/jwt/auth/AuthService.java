package com.ecom.ecom.services.jwt.auth;



public interface AuthService {

    String login(String email, String password);

    String signUp(String name, String email, String password);

    String verifyToken(String token);
}
