package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.exception.DelParqueSystemException;
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
import java.util.Optional;
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

        Optional<User> byEmail = usersRepository.findByEmail(condomino.getUser().getEmail());

        if (byEmail.isPresent()) {
            throw new DelParqueSystemException("El correo electronico le pertenece a otro condomino", "DUPLICATE_EMAIL");
        }

        usersService.register(condomino.getUser());

        return CondominoMapper.entityToDto(condominosRepository.save(CondominoMapper.dtoToEntity(condomino)));
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

    private Condomino addExtraInfo(com.example.delparque.model.Condomino condomino) {
        Condomino c = CondominoMapper.entityToDto(condomino);
        User user = usersRepository.findById(c.getUser().getId()).orElseThrow();

        c.getUser().setName(user.getName());
        c.getUser().setEmail(user.getEmail());
        c.getUser().setEmergencyNumber(user.getEmergencyNumber());
        c.getUser().setTelephoneNumber(user.getTelephoneNumber());
        return c;
    }
}