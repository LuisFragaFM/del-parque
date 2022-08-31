package com.example.delparque.controllers;

import com.example.delparque.dto.Paquete;
import com.example.delparque.service.PaquetesService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/paquetes")
public class PaquetesController {

    private final PaquetesService paquetesService;

    PaquetesController(PaquetesService paquetesService) {
        this.paquetesService = paquetesService;
    }

    @GetMapping()
    public Page<Paquete> findAll(@RequestParam Integer page) {
        return paquetesService.findAll(page);
    }

    @GetMapping("{id}")
    public Paquete findById(@PathVariable String id) {
        return paquetesService.findById(id);
    }

    @PostMapping()
    public Paquete save(@RequestBody Paquete paquete) {
        return paquetesService.save(paquete);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        paquetesService.delete(id);
    }
}
