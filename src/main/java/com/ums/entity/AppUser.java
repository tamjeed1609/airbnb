package com.ums.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length = 150)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @JsonIgnore
    @Column(name = "user_role", nullable = false, length = 20)
    private String userRole;

}