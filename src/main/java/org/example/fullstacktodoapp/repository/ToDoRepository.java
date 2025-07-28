package org.example.fullstacktodoapp.repository;

import org.example.fullstacktodoapp.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
