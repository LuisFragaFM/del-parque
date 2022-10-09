package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guardia {
    private String id;
    private String name;
    private String email;
    private String telephoneNumber;
    private String emergencyNumber;
    private String userId;
    private String role;
}