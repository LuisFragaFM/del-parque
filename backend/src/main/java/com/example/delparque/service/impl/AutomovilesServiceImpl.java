package com.example.delparque.service.impl;

import com.example.delparque.repository.AutomovilesRepository;
import com.example.delparque.service.AutomovilesService;
import org.springframework.stereotype.Service;

@Service
public class AutomovilesServiceImpl implements AutomovilesService {

    private final AutomovilesRepository automovilesRepository;

    public AutomovilesServiceImpl(AutomovilesRepository automovilesRepository) {
        this.automovilesRepository = automovilesRepository;
    }

}
