package com.Planova.PlanovaCode.infrastructure.mapper;

import com.Planova.PlanovaCode.domain.models.User;
import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
}
