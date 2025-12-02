package com.Planova.PlanovaCode.application.user.usecase;

import com.Planova.PlanovaCode.domain.models.User;
import com.Planova.PlanovaCode.domain.ports.in.user.LoginUserUseCase;
import com.Planova.PlanovaCode.domain.ports.out.UserRepositoryPort;
import com.Planova.PlanovaCode.exception.ResourceNotFoundException;
import com.Planova.PlanovaCode.shared.dto.auth.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    private final UserRepositoryPort userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public User login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        return userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + loginRequest.getEmail()));
    }
}
