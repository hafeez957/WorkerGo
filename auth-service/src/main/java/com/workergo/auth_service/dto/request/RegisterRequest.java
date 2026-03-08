package com.workergo.auth_service.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long id;

    private String password;

    private String email;

}
