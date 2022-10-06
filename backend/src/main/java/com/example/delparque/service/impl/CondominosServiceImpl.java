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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CondominosServiceImpl implements CondominosService {

    private final CondominosRepository condominosRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UsersRepository usersRepository;
    private final UsersService usersService;


    CondominosServiceImpl(CondominosRepository condominosRepository,
                          NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          UsersRepository usersRepository,
                          UsersService usersService) {
        this.condominosRepository = condominosRepository;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @Override
    public Page<Condomino> findAll(Integer page) {

        String query = "SELECT * FROM condominos";

        BeanPropertyRowMapper<Condomino> condominoViewMapper = new BeanPropertyRowMapper<>(Condomino.class);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        Pageable pageable = PageRequest.of(page, 10);

        List<Condomino> condominos = namedParameterJdbcTemplate.query(query +
                " LIMIT " + pageable.getPageSize() +
                " OFFSET " + pageable.getOffset(), mapSqlParameterSource, condominoViewMapper);

        condominos.forEach(condomino -> {
            User user = usersRepository.findById(condomino.getUserId()).orElseThrow();
            condomino.setEmail(user.getEmail());
        });

        return new PageImpl<>(condominos, pageable, pageable.getPageSize());
    }

    @Override
    public Condomino save(Condomino condomino) {
        if (condomino.getId() == null) {
            condomino.setId(UUID.randomUUID().toString());
        }

        Optional<User> byEmail = usersRepository.findByEmail(condomino.getEmail());

        if (byEmail.isPresent()) {
            throw new DelParqueSystemException("El correo electronico le pertenece a otro condomino", "DUPLICATE_EMAIL");
        }

        User register = usersService.register(condomino.getEmail(), condomino.getRole(), condomino.getName());

        condomino.setUserId(register.getId());

        return CondominoMapper.entityToDto(condominosRepository.save(CondominoMapper.dtoToEntity(condomino)));
    }

    @Override
    public Condomino findById(String id) {
        return condominosRepository.findById(id).map(condomino -> {
            Condomino condominoDto = CondominoMapper.entityToDto(condomino);

            User user = usersRepository.findById(condomino.getUserId()).orElseThrow();
            condominoDto.setEmail(user.getEmail());
            return condominoDto;
        }).orElse(new Condomino());
    }

    @Override
    public List<Condomino> findByName(String name) {
        return condominosRepository.findByName(name)
                .stream().map(CondominoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        condominosRepository.deleteById(id);
    }
}
