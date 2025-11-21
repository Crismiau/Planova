package com.Planova.PlanovaCode.repository;


import com.Planova.PlanovaCode.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface IGenericRepository<T, ID> {

    T save(T entity);
    List<T> findAll();
    Optional<T>  findById(ID id);
    void deleteById(ID id);
}
