package com.example.delparque.service.impl;

import com.example.delparque.dto.CondominoInfo;
import com.example.delparque.dto.Visitante;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VisitantesServiceImpl implements VisitantesService {

    private final VisitantesRepository visitantesRepository;
    private final CondominosRepository condominosRepository;
    private final UsersRepository usersRepository;

    VisitantesServiceImpl(VisitantesRepository visitantesRepository,
                          CondominosRepository condominosRepository,
                          UsersRepository usersRepository) {
        this.visitantesRepository = visitantesRepository;
        this.condominosRepository = condominosRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Visitante findById(String id) {
        return visitantesRepository.findById(id)
                .map(this::addExtraInfo)
                .orElse(null);
    }

    @Override
    public List<Visitante> findAllByUserId(String userId) {
        return visitantesRepository.findAllByUserId(userId).stream()
                .map(this::addExtraInfo)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Visitante> findAllByAuthorized(Integer page, String userId) {

        List<Visitante> visitantes = visitantesRepository.findAllByAuthorizedIsAndUserId(false, userId).stream()
                .map(this::addExtraInfo).toList();

        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), visitantes.size());

        return new PageImpl<>(
                visitantes.subList(start, end),
                pageable, visitantes.size()
        );
    }

    @Override
    public Page<Visitante> findAllByAuthorized(Integer page) {

        List<Visitante> visitantes = visitantesRepository.findAllByAuthorizedIsAndArrivalDateIs(false, LocalDate.now()).stream()
                .map(this::addExtraInfo).toList();

        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), visitantes.size());

        return new PageImpl<>(
                visitantes.subList(start, end),
                pageable, visitantes.size()
        );
    }

    @Override
    public Page<Visitante> findAllByGoneOut(Integer page) {

        List<Visitante> visitantes = visitantesRepository.findAllByGoneOutIs(false).stream()
                .map(this::addExtraInfo).toList();

        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), visitantes.size());

        return new PageImpl<>(
                visitantes.subList(start, end),
                pageable, visitantes.size()
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

        com.example.delparque.model.Condomino condomino = condominosRepository.findByUserId(v.getUserId()).orElseThrow();
        User user = usersRepository.findById(condomino.getUserId()).orElseThrow();

        CondominoInfo condominoInfo = CondominoInfo.builder()
                .userId(user.getId())
                .houseStreet(condomino.getStreet())
                .houseNumber(condomino.getHouseNumber())
                .owner(user.getName())
                .build();

        visitante.setCondomino(condominoInfo);
        return visitante;
    }
}
