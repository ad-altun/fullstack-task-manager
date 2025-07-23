package org.example.springrecaptodo.controller;

import org.example.springrecaptodo.dto.ToDoDto;
import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    ToDoService toDoService;

    public ToDoController(ToDoService service) {
        this.toDoService = service;
    }

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDoDto toDo) {
        return toDoService.addToDo(toDo);
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id) throws NoSuchElementException {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable String id, @RequestBody ToDoDto newToDo) {
        return toDoService.updateToDo(id, newToDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }



}
