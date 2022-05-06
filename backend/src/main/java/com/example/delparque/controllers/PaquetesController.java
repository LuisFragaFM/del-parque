package com.example.delparque.controllers;

import com.example.delparque.dto.Paquete;
import com.example.delparque.service.PaquetesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paquetes")
public class PaquetesController {

    private final PaquetesService paquetesService;

    public PaquetesController(PaquetesService paquetesService) {
        this.paquetesService = paquetesService;
    }

    @GetMapping()
    public List<Paquete> findAll() {
        return paquetesService.findAll();
    }

    @GetMapping("/{status}")
    public List<Paquete> findAllByStatus(@PathVariable Boolean status) {
        return paquetesService.findAllByEntregado(status);
    }

    @PostMapping()
    public Paquete save(@RequestBody Paquete paquete) {
        return paquetesService.save(paquete);
    }
}
