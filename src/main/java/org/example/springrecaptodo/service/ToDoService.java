package org.example.springrecaptodo.service;

import org.example.springrecaptodo.dto.ToDoDto;
import org.example.springrecaptodo.model.ToDo;
import org.example.springrecaptodo.model.ToDoStatus;
import org.example.springrecaptodo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoService {

    IdService idService;
    ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository repo, IdService service) {
        this.toDoRepository = repo;
        this.idService = service;
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDoDto newToDo) {
        ToDo toDo = new ToDo(
                idService.generateId(),
                newToDo.description(),
                newToDo.status());

        toDoRepository.save(toDo);
        return toDo;
    }

    public ToDo getToDoById(String id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID: '" + id + "' couldn't found!"));
    }

    public ToDo updateToDo(String id, ToDoDto newData) {
        ToDo existingToDo = toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ToDo with ID " + id + " not found."));

        existingToDo.setDescription(newData.description());
        existingToDo.setStatus(newData.status());

        return toDoRepository.save(existingToDo);
    }

    public ToDo deleteToDo(String id) {
        if (!toDoRepository.existsById(id)) {
            throw new NoSuchElementException("ToDo with ID " + id + " not found.");
        }
        ToDo toBeDeleted = toDoRepository.findById(id).get();
        toDoRepository.deleteById(id);
        return toBeDeleted;
    }

}
