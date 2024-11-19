package com.example.Kanban.repository;
import com.example.Kanban.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
