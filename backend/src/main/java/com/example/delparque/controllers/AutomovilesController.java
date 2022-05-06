package com.example.delparque.controllers;

import com.example.delparque.dto.Automovil;
import com.example.delparque.service.AutomovilesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/automoviles")
public class AutomovilesController {

    private final AutomovilesService automovilesService;

    public AutomovilesController(AutomovilesService automovilesService) {
        this.automovilesService = automovilesService;
    }

    @GetMapping()
    public List<Automovil> findAll() {
        return automovilesService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Automovil> findById(@PathVariable String id) {
        return automovilesService.findById(id);
    }

    @PostMapping()
    public Automovil save(@RequestBody Automovil automovil) {
        return automovilesService.save(automovil);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        automovilesService.delete(id);
    }

}
