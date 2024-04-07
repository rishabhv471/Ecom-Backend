package com.ecom.ecom.repository;

import com.ecom.ecom.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    boolean existsByEmail(String email);
    Optional<AppUser> findByEmail(String email);
}
