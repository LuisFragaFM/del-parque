package com.example.delparque.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String email;

    private String name;

    private String password;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "emergency_number")
    private String emergencyNumber;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private String role;

    private String picture;
}