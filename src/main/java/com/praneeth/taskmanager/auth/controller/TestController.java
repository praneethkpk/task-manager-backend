package com.praneeth.taskmanager.auth.controller;

import com.praneeth.taskmanager.auth.service.JwtService;
import com.praneeth.taskmanager.user.entity.User;
import com.praneeth.taskmanager.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JwtService jwtService;

    @GetMapping("/test-jwt")
    public String testJwt() {

        User user = User.builder()
                .id(1L)
                .email("praneeth@gmail.com")
                .role(UserRole.USER)
                .build();

        return jwtService.generateToken(user);
    }
    @GetMapping("/test")
    public String test() {
        return "JWT Filter Working";
    }
}