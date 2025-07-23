package org.example.springrecaptodo.controller;

import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    ToDoService toDoService;

    public  ToDoController(ToDoService service) {
        this.toDoService = service;
    }

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo) {
        return toDoService.addToDo(toDo);
    }
}
