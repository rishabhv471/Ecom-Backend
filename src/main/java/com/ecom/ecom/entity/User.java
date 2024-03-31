package com.ecom.ecom.entity;

import com.ecom.ecom.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
