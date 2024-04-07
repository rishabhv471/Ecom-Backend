package com.ecom.ecom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "users-data", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "email")
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;
}

