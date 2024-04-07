package com.ecom.ecom.services.jwt.userdetails;

import com.ecom.ecom.repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var appUser = appUserRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new User(appUser.getEmail(), appUser.getPassword(), appUser.getAuthorities());
    }
}