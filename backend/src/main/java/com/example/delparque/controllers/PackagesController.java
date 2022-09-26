package com.example.delparque.controllers;

import com.example.delparque.dto.Paquete;
import com.example.delparque.service.PackageService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/paquetes")
public class PackagesController {

    private final PackageService packageService;

    PackagesController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Page<Paquete> findAll(@RequestParam Integer page) {
        return packageService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Paquete findById(@PathVariable String id) {
        return packageService.findById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Paquete save(@RequestBody Paquete paquete) {
        return packageService.save(paquete);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        packageService.delete(id);
    }

}
