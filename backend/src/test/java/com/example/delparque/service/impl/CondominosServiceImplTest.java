package com.example.delparque.service.impl;

import com.example.delparque.model.Condomino;
import com.example.delparque.model.User;
import com.example.delparque.repository.CondominosRepository;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.service.CondominosService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CondominosServiceImplTest {

    @Test
    public void givenAEmptyList_whenFindAll_thenReturnEmptyList() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, null, null);

        Integer page = 0;

        List<Condomino> condominos = new LinkedList<>();

        Mockito.when(condominosRepository.findAll()).thenReturn(condominos);

        // When
        Page<com.example.delparque.dto.Condomino> all = condominosService.findAll(page);

        // Then
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.getTotalElements()).isEqualTo(0);
        Assertions.assertThat(all.getTotalPages()).isEqualTo(0);
        Mockito.verify(condominosRepository).findAll();
        Mockito.verifyNoMoreInteractions(condominosRepository);
    }

    @Test
    public void givenAListWithTwoElements_whenFindAll_thenReturnAListWithTwoElements() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, usersRepository, null);

        Integer page = 0;

        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();

        List<Condomino> condominos = new LinkedList<>();

        Condomino condomino1 = Condomino.builder()
                .id("1")
                .userId(userId1)
                .paidStatus(true)
                .houseNumber("1")
                .build();

        Condomino condomino2 = Condomino.builder()
                .id("2")
                .userId(userId2)
                .paidStatus(true)
                .houseNumber("2")
                .build();

        Optional<User> user1 = Optional.of(User.builder()
                        .id(userId1)
                        .role("ROLE_ADMIN")
                        .emergencyNumber("123456789")
                .build());

        Optional<User> user2 = Optional.of(User.builder()
                .id(userId2)
                .role("ROLE_ADMIN")
                .emergencyNumber("023456789")
                .build());


        condominos.add(condomino1);
        condominos.add(condomino2);

        Mockito.when(condominosRepository.findAll()).thenReturn(condominos);
        Mockito.when(usersRepository.findById(userId1)).thenReturn(user1);
        Mockito.when(usersRepository.findById(userId2)).thenReturn(user2);

        // When
        Page<com.example.delparque.dto.Condomino> all = condominosService.findAll(page);

        // Then
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.getTotalElements()).isEqualTo(2);
        Assertions.assertThat(all.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(all.getContent().get(0)).isNotNull();
        Assertions.assertThat(all.getContent().get(1)).isNotNull();
        Assertions.assertThat(all.getContent().get(0).getId()).isEqualTo("1");
        Assertions.assertThat(all.getContent().get(1).getId()).isEqualTo("2");
        Assertions.assertThat(all.getContent().get(0).getUser().getId()).isEqualTo(userId1);
        Assertions.assertThat(all.getContent().get(1).getUser().getId()).isEqualTo(userId2);
        Assertions.assertThat(user1).isNotNull();
        Assertions.assertThat(user2).isNotNull();
        Mockito.verify(condominosRepository).findAll();
        Mockito.verify(usersRepository).findById(userId1);
        Mockito.verify(usersRepository).findById(userId2);
        Mockito.verifyNoMoreInteractions(usersRepository);
        Mockito.verifyNoMoreInteractions(condominosRepository);
    }
}
