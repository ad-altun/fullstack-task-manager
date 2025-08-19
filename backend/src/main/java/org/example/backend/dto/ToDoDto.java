package org.example.backend.dto;

import org.example.backend.model.ToDoStatus;

public record ToDoDto(String description, ToDoStatus status) {
}
