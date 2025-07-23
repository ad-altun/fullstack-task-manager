package org.example.springrecaptodo.service;

import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository repo) {
        this.toDoRepository = repo;
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDo newToDo) {
        return toDoRepository.save(newToDo);
    }

    public Optional<ToDo> getToDoById(String id) {
        return toDoRepository.findById(id);
    }

}
