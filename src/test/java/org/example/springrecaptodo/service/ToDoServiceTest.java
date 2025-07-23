package org.example.springrecaptodo.service;

import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.model.ToDoStatus;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoRepository toDoRepository = mock(ToDoRepository.class);

    ToDoService toDoService = new ToDoService(toDoRepository);

    @Test
    void getToDos_whenCalled() {
        // given
        ToDo toDo1 = new ToDo("1", "td-1", ToDoStatus.OPEN);
        ToDo toDo2 = new ToDo("2", "td-2", ToDoStatus.IN_PROGRESS);
        ToDo toDo3 = new ToDo("3", "td-3", ToDoStatus.DONE);

        List<ToDo> expected = List.of(toDo1, toDo2, toDo3);
        when(toDoRepository.findAll()).thenReturn(expected);

        // when
        List<ToDo> actual = toDoService.getToDos();

        // then
        verify(toDoRepository).findAll();
        assertEquals(expected, actual);
    }

}