package org.example.springrecaptodo.controller;

import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.model.ToDoStatus;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    void getTodos_shouldReturnListOfToDos_whenCalled() throws Exception {

        // given
        ToDo toDo = new ToDo("1", "description", ToDoStatus.OPEN);
        toDoRepository.save(toDo);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
        // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                  {
                                    "id": "1",
                                    "description": "description",
                                    "status": "OPEN"
                                  }
                                ]
                                """
                ));
    }

}