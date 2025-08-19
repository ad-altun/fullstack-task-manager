package org.example.fullstacktodoapp.dto;

import org.example.fullstacktodoapp.model.ToDoStatus;

public record ToDoDto(String description, ToDoStatus status) {
}
