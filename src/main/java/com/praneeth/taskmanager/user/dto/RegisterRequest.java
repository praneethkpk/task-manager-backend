package com.praneeth.taskmanager.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank
        String name,

        @Email
        String email,

        @Size(min = 8)
        String password

) {
}