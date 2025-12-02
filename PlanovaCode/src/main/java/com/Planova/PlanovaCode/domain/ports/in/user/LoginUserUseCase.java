package com.Planova.PlanovaCode.domain.ports.in.user;

import com.Planova.PlanovaCode.domain.models.User;
import com.Planova.PlanovaCode.shared.dto.auth.LoginRequest;

public interface LoginUserUseCase {
    User login(LoginRequest loginRequest);
}
