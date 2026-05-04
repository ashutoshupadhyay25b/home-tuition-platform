package com.tuition.app.service;

import com.tuition.app.dto.UserDTO;
import com.tuition.app.entity.Role;
import com.tuition.app.entity.User;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists!");
        }
        User savedUser = userRepository.save(user);
        return AppMapper.toUserDTO(savedUser);
    }

    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return AppMapper.toUserDTO(user);
    }

    public UserDTO updateRole(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(role);
        User updatedUser = userRepository.save(user);
        return AppMapper.toUserDTO(updatedUser);
    }
}
