package com.praneeth.taskmanager.user.service;

import com.praneeth.taskmanager.user.dto.RegisterRequest;
import com.praneeth.taskmanager.user.dto.UserResponse;
import com.praneeth.taskmanager.user.dto.LoginRequest;
import com.praneeth.taskmanager.user.dto.LoginResponse;
import com.praneeth.taskmanager.auth.dto.AuthResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);
    AuthResponse login(
            LoginRequest request
    );
}