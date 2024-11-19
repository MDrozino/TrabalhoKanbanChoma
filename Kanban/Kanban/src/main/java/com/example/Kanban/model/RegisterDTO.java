package com.example.Kanban.model;

public record RegisterDTO(String login, String password, UserRoles role) {
}