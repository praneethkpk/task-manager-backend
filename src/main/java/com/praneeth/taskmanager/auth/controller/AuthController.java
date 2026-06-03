package com.praneeth.taskmanager.auth.controller;

import com.praneeth.taskmanager.user.dto.LoginRequest;
import com.praneeth.taskmanager.user.dto.LoginResponse;
import com.praneeth.taskmanager.user.dto.RegisterRequest;
import com.praneeth.taskmanager.user.dto.UserResponse;
import com.praneeth.taskmanager.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.praneeth.taskmanager.auth.dto.AuthResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(
            @Valid
            @RequestBody
            LoginRequest request
    ) {

        AuthResponse response =
                userService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid
            @RequestBody
            RegisterRequest request
    ) {

        UserResponse response =
                userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/test")
    public String test() {
        return "JWT Filter Working";
    }
}