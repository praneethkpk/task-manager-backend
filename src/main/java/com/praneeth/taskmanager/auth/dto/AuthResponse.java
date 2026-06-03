package com.praneeth.taskmanager.auth.dto;

public record AuthResponse(

        String token,

        Long id,

        String name,

        String email

) {
}