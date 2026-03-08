package com.workergo.auth_service.service;

import com.workergo.auth_service.dto.request.LoginRequest;
import com.workergo.auth_service.dto.request.RegisterRequest;
import com.workergo.auth_service.dto.response.AuthResponse;
import com.workergo.auth_service.entity.User;
import com.workergo.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final User user;





    @Override
    public AuthResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user1 -> {
                    throw new RuntimeException("email id already exists ");
                });

        User user=User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toLowerCase())
                .enabled(true)
                .build();

        userRepository.save(user);

        return new AuthResponse( "User Registered successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("Invalid email"));

        if( request.getRole().equalsIgnoreCase(user.getRole())){
            if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
                throw new RuntimeException("Invalid password");
            }
        }else{
            throw new RuntimeException("Invalid role");
        }


        return new AuthResponse("Login success");
    }
}
