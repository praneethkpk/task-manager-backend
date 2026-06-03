package com.praneeth.taskmanager.user.service;

import com.praneeth.taskmanager.common.exception.EmailAlreadyExistsException;
import com.praneeth.taskmanager.user.dto.RegisterRequest;
import com.praneeth.taskmanager.user.dto.UserResponse;
import com.praneeth.taskmanager.user.entity.User;
import com.praneeth.taskmanager.user.entity.UserRole;
import com.praneeth.taskmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.praneeth.taskmanager.common.exception.InvalidCredentialsException;
import com.praneeth.taskmanager.user.dto.LoginRequest;
import com.praneeth.taskmanager.user.dto.LoginResponse;
import com.praneeth.taskmanager.auth.service.JwtService;
import com.praneeth.taskmanager.auth.dto.AuthResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse login(
            LoginRequest request
    ) {

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(
                        InvalidCredentialsException::new
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new InvalidCredentialsException();
        }

        String token =
                jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(
                    request.email()
            );
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(
                        passwordEncoder.encode(
                                request.password()
                        )
                )
                .role(UserRole.USER)
                .build();

        User savedUser =
                userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
}