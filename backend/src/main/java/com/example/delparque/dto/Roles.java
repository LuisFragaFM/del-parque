package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    private String id;
    private Boolean administrador;
    private Boolean vigilante;
    private Boolean residente;
}
