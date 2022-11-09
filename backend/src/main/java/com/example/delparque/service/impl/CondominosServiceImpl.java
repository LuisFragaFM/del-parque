package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.model.User;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.service.CondominosService;
import com.example.delparque.service.UsersService;
import com.example.delparque.service.mappers.CondominoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CondominosServiceImpl implements CondominosService {

    private final CondominosRepository condominosRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    CondominosServiceImpl(CondominosRepository condominosRepository,
                          UsersRepository usersRepository,
                          UsersService usersService) {
        this.condominosRepository = condominosRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @Override
    public Page<Condomino> findAll(Integer page) {

        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                condominosRepository.findAll().stream()
                        .map(this::addExtraInfo)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Condomino save(Condomino condomino) {
        if (condomino.getId() == null) {
            condomino.setId(UUID.randomUUID().toString());
        }

        condomino.getUser().setRole("ROLE_RESIDENT");
        usersService.register(condomino.getUser());
        condominosRepository.save(CondominoMapper.dtoToEntity(condomino));
        return addExtraInfo(CondominoMapper.dtoToEntity(condomino));
    }

    @Override
    public Condomino findById(String id) {
        return condominosRepository.findById(id).map(this::addExtraInfo).orElse(null);
    }

    @Override
    public List<Condomino> findByName(String name) {
        return condominosRepository.findAllByName(name).stream()
                .map(this::addExtraInfo).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        condominosRepository.deleteById(id);
    }

    private Condomino addExtraInfo(com.example.delparque.model.Condomino c) {
        Condomino condomino = CondominoMapper.entityToDto(c);
        User user = usersRepository.findById(c.getUserId()).orElseThrow();

        com.example.delparque.dto.User u = com.example.delparque.dto.User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .emergencyNumber(user.getEmergencyNumber())
                .telephoneNumber(user.getTelephoneNumber())
                .build();

        condomino.setUser(u);
        return condomino;
    }
}