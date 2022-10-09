package com.example.delparque.controllers;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.service.TrabajadoresService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trabajadores")
public class TrabajadoresController {

    private final TrabajadoresService trabajadoresService;

    TrabajadoresController(TrabajadoresService trabajadoresService) {
        this.trabajadoresService = trabajadoresService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Page<Trabajador> findAll(@RequestParam Integer page) {
        return trabajadoresService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Trabajador findById(@PathVariable String id) {
        return trabajadoresService.findById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Trabajador> findByName(@PathVariable String name) {
        return trabajadoresService.findByName(name);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Trabajador save(@RequestBody Trabajador trabajador) {
        return trabajadoresService.save(trabajador);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        trabajadoresService.delete(id);
    }

}
