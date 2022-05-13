package com.example.delparque.controllers;

import com.example.delparque.dto.Condomino;
import com.example.delparque.service.CondominiosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/condominios")
public class CondominosController {

    private final CondominiosService condominiosService;

    public CondominosController(CondominiosService condominiosService) {
        this.condominiosService = condominiosService;
    }

    @GetMapping()
    public List<Condomino> findAll() {
        return condominiosService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Condomino> findById(@PathVariable String id) {
        return condominiosService.findById(id);
    }

    @PostMapping()
    public Condomino save(Condomino condomino) {
        return condominiosService.save(condomino);
    }
}
