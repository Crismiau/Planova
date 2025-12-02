package com.Planova.PlanovaCode.domain.ports.in.user;

import com.Planova.PlanovaCode.shared.dto.auth.RegisterRequest;

public interface RegisterUserUseCase {
    void register(RegisterRequest registerRequest);
}
