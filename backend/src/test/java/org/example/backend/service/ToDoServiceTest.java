package org.example.backend.service;

import org.example.backend.dto.ToDoDto;
import org.example.backend.exception.ToDoNotFoundException;
import org.example.backend.model.ToDo;
import org.example.backend.model.ToDoStatus;
import org.example.backend.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    @Test
    void getToDos_shouldReturnAllToDos_whenCalled() throws ToDoNotFoundException {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        List<ToDo> todos = List.of(
                new ToDo("1", "todo-1", ToDoStatus.OPEN),
                new ToDo("1", "todo2", ToDoStatus.OPEN)
        );

        mockRepository.saveAll(todos);

        // when
        when(mockRepository.findAll()).thenReturn(todos);
        List<ToDo> actual = testService.getToDos();

        // then
        assertEquals(todos, actual);

    }

    @Test
    void addToDo_shouldAddToRepo_whenCalledWithNewToDo() {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        ToDo toDo = new ToDo("1", "todo-1", ToDoStatus.OPEN);

        // when
        when(mockIdService.generateId()).thenReturn("1");
        when(mockRepository.save(toDo)).thenReturn(toDo);

        ToDo actual = testService.addToDo(new ToDoDto("todo-1", ToDoStatus.OPEN));

        // then
        assertEquals(toDo, actual);

        /* {
            ToDoRepository mockRepository = mock(ToDoRepository.class);
            IdService mockIdService = mock(IdService.class);
            ToDoService testService = new ToDoService(mockRepository, mockIdService);

            // given
            ToDoDto toDoDto = new ToDoDto("todo", ToDoStatus.OPEN);
            ToDo expected = new ToDo("1", "todo", ToDoStatus.OPEN);
            when(mockIdService.generateId()).thenReturn("1");

            // when
            ToDo actual = testService.addToDo(toDoDto);

            // then
            assertEquals(expected, actual);
            verify(mockRepository).save(expected);
        }
        */
    }

    @Test
    void getToDoById_shouldReturnToDo_whenCalledWithValidId() throws ToDoNotFoundException {
        ToDoRepository mockRepo = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepo, mockIdService);

        // GIVEN
        ToDo toDo = new ToDo("1", "todo-1", ToDoStatus.OPEN);
        mockRepo.save(toDo);

        // WHEN
        when(mockRepo.findById("1")).thenReturn(Optional.of(toDo));
        ToDo actual = testService.getToDoById("1");

        // Then
        assertEquals(toDo, actual);

        /*
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        ToDo expected = new ToDo("1", "todo", ToDoStatus.OPEN);

        when(mockRepository.findById("1")).thenReturn(Optional.of(expected));

        // when
        ToDo actual = testService.getToDoById("1");

        //then
        assertEquals(expected, actual);
         */
    }

    @Test
    void getToDoById_shouldThrowException_whenCalledWithInvalidId() {
        ToDoRepository mockRepo = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepo, mockIdService);

        // Given
        when(mockRepo.findById("1")).thenReturn(Optional.empty());

        // When
        try {
            testService.getToDoById("1");
            fail();
        } catch (ToDoNotFoundException e) {
            // Then
            assertTrue(true);
        }

        /*
        {
        * ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        when(mockRepository.findById("1")).thenReturn(Optional.empty());

        // when
        try {
            testService.getToDoById("1");
            fail();
        } catch (ToDoNotFoundException e) {
            // then
            assertTrue(true);
        }
        * }
        * */

    }

    @Test
    void updateToDo_shouldReturnUpdatedData_whenCalledWithValidId() throws ToDoNotFoundException {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        String existingId = "1";
        ToDo toDo = new ToDo(existingId, "description", ToDoStatus.OPEN);
        ToDoDto updatedToDo = new ToDoDto("updated desc", ToDoStatus.DONE);

        when(mockRepository.findById(existingId)).thenReturn(Optional.of(toDo));

        when(mockRepository.save(any(ToDo.class)))
                // Get the ToDo passed to save
                .thenAnswer(invocation -> {
                    ToDo savedToDo = invocation.getArgument(0);
                    // assert properties of savedTodo
                    assertEquals(existingId, savedToDo.getId());
                    assertEquals("updated desc", savedToDo.getDescription());
                    assertEquals(ToDoStatus.DONE, savedToDo.getStatus());
                    // return the modified object
                    return savedToDo;
                });

        ToDo actualUpdatedToDo = testService.updateToDo(existingId, updatedToDo);

        // when
        assertEquals(
                new ToDo(existingId, updatedToDo.description(), updatedToDo.status()),
                actualUpdatedToDo);

        verify(mockRepository).findById(existingId);
        verify(mockRepository).save(any(ToDo.class));
    }

    @Test
    void updateToDo_shouldThrowException_whenCalledWithInvalidId() {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        String invalidId = "invalid-id";
        ToDoDto updateToDo = new ToDoDto("Updated description", ToDoStatus.DONE);

        // given

        when(mockRepository.findById(invalidId)).thenReturn(Optional.empty());

//        // when
//        try {
//            testService.updateToDo(invalidId, updateToDo);
//            fail();
//        } catch (ToDoNotFoundException e) {
//            // then
//            assertTrue(true);
//        }

        assertThrows(
                ToDoNotFoundException.class,
                () -> testService.updateToDo(invalidId, updateToDo)
        );

        verify(mockRepository).findById(invalidId);
        verify(mockRepository, never()).save(any(ToDo.class));
    }

    @Test
    void deleteToDo_shouldReturnSameToDo_whenCalledWithValidId() throws ToDoNotFoundException {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        String validId = "valid-id";
        ToDo existingToDo = new ToDo(validId, "description", ToDoStatus.OPEN);

        when(mockRepository.existsById(validId)).thenReturn(true);
        when(mockRepository.findById(validId)).thenReturn(Optional.of(existingToDo));

        // when
        ToDo actual = testService.deleteToDo(validId);

        // then
        assertEquals(existingToDo, actual);
        verify(mockRepository).deleteById(validId);
    }

    @Test
    void deleteToDo_shouldThrowException_whenCalledWithInvalidId() {
        ToDoRepository mockRepository = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);
        ToDoService testService = new ToDoService(mockRepository, mockIdService);

        // given
        String invalidId = "invalid-id";
        ToDo toBeDeletedToDo = new ToDo(invalidId, "description", ToDoStatus.OPEN);

        when(mockRepository.existsById(invalidId)).thenReturn(false);

        assertThrows(ToDoNotFoundException.class,
                () -> testService.deleteToDo(invalidId));

//        verify(mockRepository).existsById(invalidId);
//        verify(mockRepository,never()).deleteById(any(String.class));
        // when


        // then

    }

}











