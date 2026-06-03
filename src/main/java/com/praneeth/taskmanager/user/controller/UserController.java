package com.praneeth.taskmanager.user.controller;

import com.praneeth.taskmanager.user.dto.RegisterRequest;
import com.praneeth.taskmanager.user.dto.UserResponse;
import com.praneeth.taskmanager.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.praneeth.taskmanager.user.dto.LoginRequest;
import com.praneeth.taskmanager.user.dto.LoginResponse;


@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



}