package com.example.Kanban.repository;

import com.example.Kanban.model.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KanbanRepository extends JpaRepository<Kanban, Long> {

}
