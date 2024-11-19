package com.example.Kanban.service;

import com.example.Kanban.model.User;
import com.example.Kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean getUser(String login) {
        List<User> usuarios = userRepository.findAll();
        for (User usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public String register(User user) {
        if (getUser(user.getLogin())) {
            return "Usuário já existe:\n" + user.toString();
        }
        else{
            userRepository.save(user);
            return "Usuário adicionado:\n" + user.toString();
        }
    }


}
