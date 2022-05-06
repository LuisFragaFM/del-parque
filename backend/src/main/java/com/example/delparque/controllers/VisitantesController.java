package com.example.delparque.controllers;

import com.example.delparque.dto.Visitante;
import com.example.delparque.service.VisitantesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/visitantes")
public class VisitantesController {

    private final VisitantesService visitantesService;

    public VisitantesController(VisitantesService visitantesService) {
        this.visitantesService = visitantesService;
    }

    @GetMapping()
    public List<Visitante> findAll() {
        return visitantesService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Visitante> findById(@PathVariable String id) {
        return visitantesService.findById(id);
    }

    @PostMapping()
    public Visitante save(@RequestBody Visitante visitante) {
        return visitantesService.save(visitante);
    }
}
