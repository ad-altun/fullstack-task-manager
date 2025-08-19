package org.example.backend.controller;

import org.example.backend.dto.ToDoDto;
import org.example.backend.exception.InvalidIdRequestedException;
import org.example.backend.exception.ToDoNotFoundException;
import org.example.backend.model.ToDo;
import org.example.backend.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    ToDoService toDoService;

    public ToDoController(ToDoService service) {
        this.toDoService = service;
    }

    @GetMapping
    public List<ToDo> getToDos() throws ToDoNotFoundException {
        if (toDoService.getToDos().isEmpty())
        {
            throw new ToDoNotFoundException("No record found!");
        }
        return toDoService.getToDos();
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDoDto toDo) {
        return toDoService.addToDo(toDo);
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id) throws ToDoNotFoundException {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable String id, @RequestBody ToDoDto newToDo)
            throws InvalidIdRequestedException {
        return toDoService.updateToDo(id, newToDo);
    }

    @DeleteMapping("/{id}")
    public ToDo deleteToDo(@PathVariable String id) throws ToDoNotFoundException {
        return toDoService.deleteToDo(id);
    }


}
