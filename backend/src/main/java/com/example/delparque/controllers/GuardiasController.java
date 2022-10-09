package com.example.delparque.controllers;


import com.example.delparque.service.GuardiasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/guardias")
public class GuardiasController {

    private final GuardiasService guardiasService;

    GuardiasController(GuardiasService guardiasService) {
        this.guardiasService = guardiasService;
    }
}
