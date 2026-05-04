package com.tuition.app.controller;

import com.tuition.app.dto.AuthResponse;
import com.tuition.app.dto.UserDTO;
import com.tuition.app.entity.Role;
import com.tuition.app.entity.User;
import com.tuition.app.security.JwtUtils;
import com.tuition.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public record LoginRequest(String email, String password) {}

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody com.tuition.app.dto.RegisterRequest request) {
        UserDTO userDto = userService.registerUser(request);
        String token = jwtUtils.generateToken(userDto.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        UserDTO userDto = userService.loginUser(request.email(), request.password());
        String token = jwtUtils.generateToken(userDto.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, userDto));
    }

    @PutMapping("/role/{userId}")
    public ResponseEntity<UserDTO> updateRole(@PathVariable Long userId, @RequestParam Role role) {
        return ResponseEntity.ok(userService.updateRole(userId, role));
    }
}
