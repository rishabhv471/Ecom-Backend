package com.ecom.ecom.services.jwt.auth;

import com.ecom.ecom.entity.AppUser;
import com.ecom.ecom.repository.AppUserRepo;
import com.ecom.ecom.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;

    @Override
    public String login(String email, String password) {
        var authToken = new UsernamePasswordAuthenticationToken(email, password);

        var authenticate = authenticationManager.authenticate(authToken);

        // Generate Token
        return JwtUtils.generateToken(((UserDetails)(authenticate.getPrincipal())).getUsername());
    }

    @Override
    public String signUp(String name, String email, String password) {
        // Check whether user already exists or not
        if(appUserRepo.existsByEmail(email)){
            throw new RuntimeException("User already exists");
        }


        // Encode password
        var encodedPassword = passwordEncoder.encode(password);

        // Authorities
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Create App user
        var appUser = AppUser.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .authorities(authorities)
                .build();


        // Save user
        appUserRepo.save(appUser);

        // Generate Token
        return JwtUtils.generateToken(email);
    }

    @Override
    public String verifyToken(String token) {
        var usernameOptional = JwtUtils.getUsernameFromToken(token);

        if(usernameOptional.isPresent()){
            return usernameOptional.get();
        }

        throw new RuntimeException("Token invalid");
    }
}