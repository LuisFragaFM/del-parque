package com.example.delparque.service.impl;

import com.example.delparque.dto.Trabajador;
import com.example.delparque.dto.WorkDay;
import com.example.delparque.model.Condomino;
import com.example.delparque.model.User;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.TrabajadoresRepository;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.repository.WorkDaysRepository;
import com.example.delparque.service.TrabajadoresService;
import com.example.delparque.service.mappers.TrabajadorMapper;
import com.example.delparque.service.mappers.WorkDayMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrabajadoresServiceImpl implements TrabajadoresService {

    private final TrabajadoresRepository trabajadoresRepository;
    private final UsersRepository usersRepository;
    private final CondominosRepository condominosRepository;
    private final WorkDaysRepository workDaysRepository;

    TrabajadoresServiceImpl(TrabajadoresRepository trabajadoresRepository,
                            UsersRepository usersRepository,
                            CondominosRepository condominosRepository,
                            WorkDaysRepository workDaysRepository) {
        this.trabajadoresRepository = trabajadoresRepository;
        this.usersRepository = usersRepository;
        this.condominosRepository = condominosRepository;
        this.workDaysRepository = workDaysRepository;
    }

    @Override
    public Page<Trabajador> findAll(Integer page) {

        Pageable pageable = PageRequest.of(page, 10);

        return new PageImpl<>(
                trabajadoresRepository.findAll().stream()
                        .map(this::addExtraInfo)
                        .collect(Collectors.toList()),
                pageable, pageable.getPageSize()
        );
    }

    @Override
    public Trabajador findById(String id) {
        return trabajadoresRepository.findById(id)
                .map(this::addExtraInfo).orElse(null);
    }

    @Override
    public List<Trabajador> findByName(String name) {
        return trabajadoresRepository.findByName(name).stream()
                .map(this::addExtraInfo)
                .collect(Collectors.toList());
    }

    @Override
    public Trabajador save(Trabajador trabajador) {
        if (trabajador.getId() == null) {
            trabajador.setId(UUID.randomUUID().toString());
        }

        trabajador.getWorkDays().forEach(workDay -> saveWorkDays(workDay, trabajador.getId()));

        return TrabajadorMapper.entityToDto(trabajadoresRepository.save(TrabajadorMapper.dtoToEntity(trabajador)));
    }

    @Override
    public void delete(String id) {
        trabajadoresRepository.deleteById(id);
    }

    private void saveWorkDays(WorkDay w, String id) {
        if (w.getId() == null) {
            w.setId(UUID.randomUUID().toString());
        }

        com.example.delparque.model.WorkDay workDay = WorkDayMapper.dtoToEntity(w);
        workDay.setTrabajadorId(id);
        workDaysRepository.save(workDay);
    }

    private Trabajador addExtraInfo(com.example.delparque.model.Trabajador t) {
        Trabajador trabajador = TrabajadorMapper.entityToDto(t);
        Condomino condomino = condominosRepository.findById(t.getCondominoId()).orElseThrow();
        User user = usersRepository.findById(condomino.getUserId()).orElseThrow();

        trabajador.getCondominoInfo().setOwner(user.getName());
        return trabajador;
    }
}
