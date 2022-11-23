package com.example.delparque.controllers;

import com.example.delparque.dto.Condomino;
import com.example.delparque.service.CondominosService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("name/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUARD', 'ROLE_RESIDENT')")
    public List<Condomino> findByName(@PathVariable String name) {
        return condominosService.findByName(name);
    }
    @GetMapping("filter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUARD', 'ROLE_RESIDENT')")
    public Optional<Condomino> findByStreetAndHouseNumber(@RequestParam() String street, @RequestParam() String houseNumber) {
        return condominosService.findByStreetAndHouseNumber(street, houseNumber);
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
