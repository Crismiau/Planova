package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.adapters;

import com.Planova.PlanovaCode.domain.models.User;
import com.Planova.PlanovaCode.domain.ports.out.UserRepositoryPort;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.UserEntity;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.repositories.UserJPARepository;
import com.Planova.PlanovaCode.infrastructure.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJPARepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomain);
    }
}
