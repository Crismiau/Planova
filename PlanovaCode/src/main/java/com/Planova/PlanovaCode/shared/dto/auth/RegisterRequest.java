package com.Planova.PlanovaCode.shared.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
