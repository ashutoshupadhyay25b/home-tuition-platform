package com.tuition.app.controller;

import com.tuition.app.entity.Role;
import com.tuition.app.entity.User;
import com.tuition.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    public record LoginRequest(String email, String password) {}

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request.email(), request.password()));
    }

    @PutMapping("/role/{userId}")
    public ResponseEntity<User> updateRole(@PathVariable Long userId, @RequestParam Role role) {
        return ResponseEntity.ok(userService.updateRole(userId, role));
    }
}
