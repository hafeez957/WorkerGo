package com.workergo.auth_service.service;


import com.workergo.auth_service.dto.request.LoginRequest;
import com.workergo.auth_service.dto.request.RegisterRequest;
import com.workergo.auth_service.entity.User;

import java.util.Optional;

public interface AuthService {
    User register(RegisterRequest request);

    User login(LoginRequest request);

}
