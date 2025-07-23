package org.example.springrecaptodo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDo {
    private String id;
    private String description;
    private ToDoStatus status;
}
