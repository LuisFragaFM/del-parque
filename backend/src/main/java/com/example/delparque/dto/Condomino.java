package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condomino {
    private String id;
    private String name;
    private String street;
    private String houseNumber;
    private String telephoneNumber;
    private String direction;
    private String email;
    private String emergencyNumber;
    private boolean paidStatus;
    private String userId;
    private String role;
}