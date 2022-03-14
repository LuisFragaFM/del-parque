package com.example.delparque.controllers;

import com.example.delparque.dto.Pago;
import com.example.delparque.service.PagosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @GetMapping()
    public List<Pago> findAll() {
        return pagosService.findAll();
    }
}
