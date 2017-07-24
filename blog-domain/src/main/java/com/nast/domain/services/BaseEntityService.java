package com.nast.domain.services;

import com.nast.domain.entities.base.BaseEntity;
import com.nast.domain.filters.Filter;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseEntityService<T extends BaseEntity> {

    T save(T target);

    boolean exists(Filter filter);

    void delete(Long id);

    void deleteAll();

    Optional<T> findOne(Filter filter);

    Optional<T> findOne(Long id);

    Page<T> findAll(Filter filter, Pageable pageable);

    Page<T> findAll(Pageable pageable);
}
