package com.Planova.PlanovaCode.application.user.usecase;

import com.Planova.PlanovaCode.domain.models.User;
import com.Planova.PlanovaCode.domain.ports.in.user.RegisterUserUseCase;
import com.Planova.PlanovaCode.domain.ports.out.UserRepositoryPort;
import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import com.Planova.PlanovaCode.shared.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User with email " + registerRequest.getEmail() + " already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("USER"); // Default role

        userRepository.save(user);
    }
}
