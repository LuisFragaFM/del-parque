package com.example.delparque.controllers;

import com.example.delparque.dto.Direccion;
import com.example.delparque.service.DireccionesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/direcciones")
public class DireccionesController {

    private final DireccionesService direccionesService;

    public DireccionesController(DireccionesService direccionesService) {
        this.direccionesService = direccionesService;
    }

    @GetMapping()
    public List<Direccion> findAll() {
        return direccionesService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Direccion> findById(@PathVariable String id) {
        return direccionesService.findById(id);
    }

    @PostMapping()
    public Direccion save(Direccion direccion) {
        return direccionesService.save(direccion);
    }
}
