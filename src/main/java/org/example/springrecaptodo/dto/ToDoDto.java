package org.example.springrecaptodo.dto;

import org.example.springrecaptodo.model.ToDoStatus;

public record ToDoDto(String description, ToDoStatus status) {
}
