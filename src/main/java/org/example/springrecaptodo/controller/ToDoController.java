package org.example.springrecaptodo.controller;

import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.service.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
