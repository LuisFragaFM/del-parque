package com.example.delparque.service.impl;

import com.example.delparque.dto.CondominoInfo;
import com.example.delparque.dto.Visitante;
import com.example.delparque.model.Condomino;
import com.example.delparque.model.User;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitanteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;
    private final UsersRepository usersRepository;
    private final CondominosRepository condominosRepository;

    VisitantesServiceImpl(VisitantesRepository visitantesRepository,
                          UsersRepository usersRepository,
                          CondominosRepository condominosRepository) {
        this.visitantesRepository = visitantesRepository;
        this.usersRepository = usersRepository;
        this.condominosRepository = condominosRepository;
    }

    @Override
    public Visitante findById(String id) {
        return visitantesRepository.findById(id)
                .map(this::addExtraInfo).orElse(null);
    }

    @Override
    public Page<Visitante> findAllByAuthorized(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByAuthorizedIs(true).stream()
                        .map(this::addExtraInfo).collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Page<Visitante> findAllByAuthorizedAndGoneOut(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByAuthorizedIsAndGoneOutIs(true, false).stream()
                        .map(this::addExtraInfo).collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Visitante save(Visitante visitante) {
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        return VisitanteMapper.entityToDto(visitantesRepository.save(VisitanteMapper.dtoToEntity(visitante)));
    }

    @Override
    public void delete(String id) {
        visitantesRepository.deleteById(id);
    }

    @Override
    public List<Visitante> findByName(String name) {
        return visitantesRepository.findByName(name).stream()
                .map(this::addExtraInfo)
                .collect(Collectors.toList());
    }

    private Visitante addExtraInfo(com.example.delparque.model.Visitante v) {
        Visitante visitante = VisitanteMapper.entityToDto(v);

        Condomino condomino = condominosRepository.findById(visitante.getCondomino().getCondominoId()).orElseThrow();
        User user = usersRepository.findById(condomino.getUserId()).orElseThrow();

        CondominoInfo condominoInfo = CondominoInfo.builder()
                .owner(user.getName())
                .build();

        visitante.setCondomino(condominoInfo);
        return visitante;
    }
}
