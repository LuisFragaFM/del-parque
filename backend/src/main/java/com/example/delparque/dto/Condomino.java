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
    private String street;
    private String relatives;
    private String houseNumber;
    private User user;
    private boolean paidStatus;
    private String role;
}