package com.example.delparque.service.impl;

import com.example.delparque.dto.Condomino;
import com.example.delparque.dto.CondominoInfo;
import com.example.delparque.dto.User;
import com.example.delparque.dto.Visitante;
import com.example.delparque.repository.VisitantesRepository;
import com.example.delparque.service.VisitantesService;
import com.example.delparque.service.mappers.VisitanteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;

    VisitantesServiceImpl(VisitantesRepository visitantesRepository) {
        this.visitantesRepository = visitantesRepository;

    }

    @Override
    public Visitante findById(String id) {
        return visitantesRepository.findById(id)
                .map(VisitanteMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public Page<Visitante> findAllByAuthorized(Integer page, String userId) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByAuthorizedIsAndUserId(false, userId).stream()
                        .map(VisitanteMapper::entityToDto)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Page<Visitante> findAllByGoneOut(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                visitantesRepository.findAllByGoneOutIs(false).stream()
                        .map(VisitanteMapper::entityToDto)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Visitante save(Visitante visitante) {
        System.out.println(visitante);
        if (visitante.getId() == null) {
            visitante.setId(UUID.randomUUID().toString());
        }

        return VisitanteMapper.entityToDto(visitantesRepository.save(VisitanteMapper.dtoToEntity(visitante)));
    }

    @Override
    public void delete(String id) {
        visitantesRepository.deleteById(id);
    }

    private Visitante addExtraInfo(com.example.delparque.model.Visitante v) {
        Visitante visitante = VisitanteMapper.entityToDto(v);

        Condomino condomino = condominosRepository.findById(visitante.getCondomino().getCondominoId()).orElseThrow();
        User user = usersRepository.findById(condomino.getUserId()).orElseThrow();

        CondominoInfo condominoInfo = CondominoInfo.builder()
                .userId(user.getId())
                .owner(user.getName())
                .build();

        visitante.setCondomino(condominoInfo);
        return visitante;
    }
}
