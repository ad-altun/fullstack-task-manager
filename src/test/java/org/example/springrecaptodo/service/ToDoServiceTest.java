package org.example.springrecaptodo.service;

import org.example.springrecaptodo.dto.ToDoDto;
import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.model.ToDoStatus;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    private final ToDoRepository mockRepository = mock(ToDoRepository.class);
    private final IdService mockIdService = mock(IdService.class);

    private final ToDoService testService = new ToDoService(mockRepository, mockIdService);

    @Test
    void getToDos_whenCalled() {
        // given
        ToDo toDo1 = new ToDo("1", "td-1", ToDoStatus.OPEN);
        ToDo toDo2 = new ToDo("2", "td-2", ToDoStatus.IN_PROGRESS);
        ToDo toDo3 = new ToDo("3", "td-3", ToDoStatus.DONE);

        List<ToDo> expected = List.of(toDo1, toDo2, toDo3);
        when(mockRepository.findAll()).thenReturn(expected);

        // when
        List<ToDo> actual = testService.getToDos();

        // then
        verify(mockRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void addToDo_shouldAddToRepo_whenCalledWithNewToDo() {
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

    @Test
    void getToDoById_shouldReturnToDo_whenCalledWithValidId() {
        // given
        ToDo expected = new ToDo("1", "todo", ToDoStatus.OPEN);

        when(mockRepository.findById("1")).thenReturn(Optional.of(expected));

        // when
        ToDo actual = testService.getToDoById("1");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getToDoById_shouldThrowException_whenCalledWithInvalidId() {
        // given
        when(mockRepository.findById("1")).thenReturn(Optional.empty());

        // when
        try {
            testService.getToDoById("1");
            fail();
        } catch (NoSuchElementException e) {
            // then
            assertTrue(true);
        }
        }

//    @Test
//    void updateToDo() {
    // given
    // when
    //then
//    }
//
//    @Test
//    void deleteToDo() {
    // given
    // when
    //then
//    }
}