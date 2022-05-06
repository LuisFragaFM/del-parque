package com.example.delparque.controllers;

import com.example.delparque.dto.Entrega;
import com.example.delparque.service.EntregasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/entregas")
public class EntregasController {

    private final EntregasService entregasService;

    public EntregasController(EntregasService entregasService) {
        this.entregasService = entregasService;
    }

    @GetMapping()
    public List<Entrega> findAll() {
        return entregasService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Entrega> findById(@PathVariable String id) {
        return entregasService.findById(id);
    }

    @PostMapping()
    public Entrega save(Entrega entrega) {
        return entregasService.save(entrega);
    }
}
