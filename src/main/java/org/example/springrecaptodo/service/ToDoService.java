package org.example.springrecaptodo.service;

import org.example.springrecaptodo.dto.ToDoDto;
import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ToDoService {

    IdService idService;
    ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository repo ) {
        this.toDoRepository = repo;
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDoDto newToDo) {
        ToDo temp = new ToDo(idService.generateId(), newToDo.description(), newToDo.status());
        return toDoRepository.save(temp);
    }

    public ToDo getToDoById(String id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID: '" + id + "' couldn't found!"));
    }

    public ToDo updateToDo(String id, ToDoDto newData) {
        toDoRepository.deleteToDoById(id);

        ToDo newToDo = new ToDo(id, newData.description(), newData.status());

        return toDoRepository.save(newToDo);
    }

    public void deleteToDo(String id) {
        toDoRepository.deleteToDoById(id);
    }

}
