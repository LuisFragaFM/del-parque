package com.example.delparque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {
    private String id;
    private String email;
    private String numeroTelefono;
    private String telefonoEmergencia;
}
