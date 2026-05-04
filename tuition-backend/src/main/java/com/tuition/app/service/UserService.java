package com.tuition.app.service;

import com.tuition.app.dto.UserDTO;
import com.tuition.app.entity.Role;
import com.tuition.app.entity.User;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerUser(com.tuition.app.dto.RegisterRequest request) {
        System.out.println("Starting registration for email: " + request.getEmail());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Registration failed: Email already exists -> " + request.getEmail());
            throw new RuntimeException("User with this email already exists!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(com.tuition.app.entity.Role.valueOf(request.getRole()));

        User savedUser = userRepository.save(user);
        System.out.println("Registration successful for user: " + savedUser.getId());
        return AppMapper.toUserDTO(savedUser);
    }

    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
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
