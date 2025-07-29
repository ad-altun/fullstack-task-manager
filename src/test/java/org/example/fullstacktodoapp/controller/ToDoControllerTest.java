package org.example.fullstacktodoapp.controller;

import org.example.fullstacktodoapp.model.ToDo;
import org.example.fullstacktodoapp.model.ToDoStatus;
import org.example.fullstacktodoapp.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
        ToDo toDo = new ToDo("", "description", ToDoStatus.OPEN);
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

    @Test
    void getTodos_shouldThrowToDoNotFoundException_WhenCalledWithEmptyRepo() throws Exception{
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
        // then
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No record found!"));

    }

    @Test
    void addToDo_shouldReturnAddedToDo_whenCalledWithNewToDoDto() throws Exception {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                  "description": "new description",
                                  "status": "OPEN"
                                  }
                                """))
                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                          {
                                                          "description": "new description",
                                                          "status": "OPEN"
                                                          }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

}