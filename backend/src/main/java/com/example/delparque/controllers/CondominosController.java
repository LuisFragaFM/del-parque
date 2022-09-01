package com.example.delparque.controllers;

import com.example.delparque.dto.Condomino;
import com.example.delparque.service.CondominosService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/condominos")
public class CondominosController {

    private final CondominosService condominosService;

    CondominosController(CondominosService condominosService) {
        this.condominosService = condominosService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Page<Condomino> findAll(@RequestParam Integer page) {
        return condominosService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Condomino findById(@PathVariable String id) {
        return condominosService.findById(id);
    }

    @GetMapping("nombre")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Condomino findByNombre(@RequestParam() String nombre) {
        return condominosService.findByNombre(nombre);
    }

    @GetMapping("telefono")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Condomino findByNumeroTelefono(@RequestParam() String telefono) {
        return condominosService.findByNumeroTelefono(telefono);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Condomino save(@RequestBody Condomino condomino) {
        return condominosService.save(condomino);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        condominosService.delete(id);
    }
}
