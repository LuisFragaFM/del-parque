package com.example.delparque.controllers;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.service.TrabajadoresService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trabajadores")
public class TrabajadoresController {

    private final TrabajadoresService trabajadoresService;

    TrabajadoresController(TrabajadoresService trabajadoresService) {
        this.trabajadoresService = trabajadoresService;
    }

    @GetMapping()
    public Page<Trabajador> findAll(@RequestParam Integer page) {
        return trabajadoresService.findAll(page);
    }

    @GetMapping("{id}")
    public Trabajador findById(@PathVariable String id) {
        return trabajadoresService.findById(id);
    }

    @GetMapping("/nombre")
    public Trabajador findByNombreTrabajador(@RequestParam(name = "nombre") String nombre) {
        return trabajadoresService.findByName(nombre);
    }

    @PostMapping()
    public Trabajador save(@RequestBody Trabajador trabajador) {
        return trabajadoresService.save(trabajador);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        trabajadoresService.delete(id);
    }

}
