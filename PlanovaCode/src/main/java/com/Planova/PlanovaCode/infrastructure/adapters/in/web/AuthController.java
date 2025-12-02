package com.Planova.PlanovaCode.infrastructure.adapters.in.web;

import com.Planova.PlanovaCode.domain.ports.in.user.LoginUserUseCase;
import com.Planova.PlanovaCode.domain.ports.in.user.RegisterUserUseCase;
import com.Planova.PlanovaCode.infrastructure.security.JwtService;
import com.Planova.PlanovaCode.shared.dto.auth.LoginRequest;
import com.Planova.PlanovaCode.shared.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        registerUserUseCase.register(req);
        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var user = loginUserUseCase.login(req);
        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
