package com.example.delparque.controllers;

import com.example.delparque.dto.Condominio;
import com.example.delparque.service.CondominiosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/condominios")
public class CondominiosController {

    private final CondominiosService condominiosService;

    public CondominiosController(CondominiosService condominiosService) {
        this.condominiosService = condominiosService;
    }

    @GetMapping()
    public List<Condominio> findAll() {
        return condominiosService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Condominio> findById(@PathVariable String id) {
        return condominiosService.findById(id);
    }

    @PostMapping()
    public Condominio save(Condominio condominio) {
        return condominiosService.save(condominio);
    }
}
