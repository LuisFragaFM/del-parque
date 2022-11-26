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

import java.util.*;

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
                .name("UserName1")
                .role("ROLE_ADMIN")
                .emergencyNumber("123456789")
                .build());

        Optional<User> user2 = Optional.of(User.builder()
                .id(userId2)
                .name("UserName2")
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
        Assertions.assertThat(user1.get().getId()).isEqualTo(userId1);
        Assertions.assertThat(user1.get().getName()).isEqualTo("UserName1");
        Assertions.assertThat(user1.get().getRole()).isEqualTo("ROLE_ADMIN");
        Assertions.assertThat(user1.get().getEmergencyNumber()).isEqualTo("123456789");
        Assertions.assertThat(user2).isNotNull();
        Assertions.assertThat(user2.get().getId()).isEqualTo(userId2);
        Assertions.assertThat(user2.get().getName()).isEqualTo("UserName2");
        Assertions.assertThat(user2.get().getRole()).isEqualTo("ROLE_ADMIN");
        Assertions.assertThat(user2.get().getEmergencyNumber()).isEqualTo("023456789");

        Mockito.verify(condominosRepository).findAll();
        Mockito.verify(usersRepository).findById(userId1);
        Mockito.verify(usersRepository).findById(userId2);
        Mockito.verifyNoMoreInteractions(usersRepository);
        Mockito.verifyNoMoreInteractions(condominosRepository);
    }

    @Test
    public void givenAListWithElevenElements_whenFindAll_thenReturnAListWithTwoElementsWithTwoPages() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, usersRepository, null);

        Integer page = 0;

        String userId1 = UUID.randomUUID().toString();

        List<Condomino> condominos = new LinkedList<>();

        for (int i = 0; i < 11; i++) {
            Condomino condomino = Condomino.builder()
                    .id(String.valueOf(i))
                    .userId(userId1)
                    .paidStatus(true)
                    .houseNumber(String.valueOf(i))
                    .build();

            condominos.add(condomino);
        }

        Optional<User> user1 = Optional.of(User.builder()
                .id(userId1)
                .name("UserName1")
                .role("ROLE_ADMIN")
                .emergencyNumber("123456789")
                .build());

        Mockito.when(condominosRepository.findAll()).thenReturn(condominos);
        Mockito.when(usersRepository.findById(userId1)).thenReturn(user1);

        // When
        Page<com.example.delparque.dto.Condomino> all = condominosService.findAll(page);

        // Then
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.getTotalElements()).isEqualTo(11);
        Assertions.assertThat(all.getTotalPages()).isEqualTo(2);

        Assertions.assertThat(user1).isNotNull();
        Assertions.assertThat(user1.get().getId()).isEqualTo(userId1);
        Assertions.assertThat(user1.get().getName()).isEqualTo("UserName1");
        Assertions.assertThat(user1.get().getRole()).isEqualTo("ROLE_ADMIN");
        Assertions.assertThat(user1.get().getEmergencyNumber()).isEqualTo("123456789");

        Mockito.verify(condominosRepository).findAll();
    }

    @Test
    public void givenACorrectId_whenFindById_thenReturnCondomino() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, usersRepository, null);

        String userId1 = UUID.randomUUID().toString();
        String id = UUID.randomUUID().toString();

        Condomino condomino = Condomino.builder()
                .id(id)
                .userId(userId1)
                .paidStatus(true)
                .houseNumber("1")
                .build();

        Optional<User> user1 = Optional.of(User.builder()
                .id(userId1)
                .name("UserName1")
                .role("ROLE_ADMIN")
                .emergencyNumber("123456789")
                .build());

        Mockito.when(condominosRepository.findById(id)).thenReturn(Optional.ofNullable(condomino));
        Mockito.when(usersRepository.findById(userId1)).thenReturn(user1);

        // When
        com.example.delparque.dto.Condomino condominoDto = condominosService.findById(id);

        // Then
        Assertions.assertThat(condominoDto).isNotNull();
        Assertions.assertThat(condominoDto.getId()).isEqualTo(id);
        Assertions.assertThat(condominoDto.getUser().getRole()).isEqualTo("ROLE_ADMIN");
        Assertions.assertThat(condominoDto.getUser().getId()).isEqualTo(userId1);
        Assertions.assertThat(condominoDto.isPaidStatus()).isTrue();
        Assertions.assertThat(condominoDto.getHouseNumber()).isEqualTo("1");

        Mockito.verify(condominosRepository).findById(id);
        Mockito.verify(usersRepository).findById(userId1);
    }

    @Test
    public void givenAWrongId_whenFindById_thenReturnNull() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, usersRepository, null);

        String id = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        Mockito.when(condominosRepository.findById(id)).thenReturn(Optional.of(new Condomino()));
        Mockito.doThrow(NoSuchElementException.class).when(usersRepository).findById(userId);

        // When
        // Then
        Assertions.assertThatThrownBy(() -> condominosService.findById(id))
                .isInstanceOf(NoSuchElementException.class);

        Mockito.verify(condominosRepository).findById(id);
    }

    @Test
    public void givenAName_whenFindByName_thenReturnAListWithTwoElements() {

        // Given
        CondominosRepository condominosRepository = Mockito.mock(CondominosRepository.class);
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

        CondominosService condominosService = new
                CondominosServiceImpl(condominosRepository, usersRepository, null);

        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();

        List<Condomino> condominos = new LinkedList<>();

        Condomino condomino1 = Condomino.builder()
                .userId(userId1)
                .build();

        Condomino condomino2 = Condomino.builder()
                .userId(userId2)
                .build();

        User user1 = User.builder()
                .id(userId1)
                .name("Edson")
                .build();

        User user2 = User.builder()
                .id(userId2)
                .name("Edgar")
                .build();

        condominos.add(condomino1);
        condominos.add(condomino2);

        Mockito.when(usersRepository.findById(userId1)).thenReturn(Optional.ofNullable(user1));
        Mockito.when(usersRepository.findById(userId2)).thenReturn(Optional.ofNullable(user2));
        Mockito.when(condominosRepository.findAllByName("Ed")).thenReturn(condominos);

        // When
        List<com.example.delparque.dto.Condomino> condominoList = condominosService.findByName("Ed");

        // Then
        Assertions.assertThat(condominoList).isNotNull();
        Assertions.assertThat(condominoList.size()).isEqualTo(2);
        Assertions.assertThat(condominoList.get(0)).isNotNull();
        Assertions.assertThat(condominoList.get(1)).isNotNull();
        Assertions.assertThat(condominoList.get(0).getUser().getName()).isEqualTo("Edson");
        Assertions.assertThat(condominoList.get(1).getUser().getName()).isEqualTo("Edgar");
    }
}
