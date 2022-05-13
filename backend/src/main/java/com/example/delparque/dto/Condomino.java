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
    private String idUsuario;
    private String idDireccion;
    private String idTrabajador;
}
