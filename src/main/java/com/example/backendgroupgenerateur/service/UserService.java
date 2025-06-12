package com.example.backendgroupgenerateur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backendgroupgenerateur.model.User;
import com.example.backendgroupgenerateur.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        // Ici tu peux ajouter la logique pour hasher le password plus tard
        return userRepository.save(user);
    }
}
