package com.example.delparque.controllers;

import com.example.delparque.dto.Package;
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
    public Page<Package> findAll(@RequestParam Integer page) {
        return packageService.findAll(page);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Package findById(@PathVariable String id) {
        return packageService.findById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public Package save(@RequestBody Package aPackage) {
        return packageService.save(aPackage);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_GUARD', 'ROLE_ADMIN')")
    public void delete(@PathVariable String id) {
        packageService.delete(id);
    }

}
