package com.nast.domain.services;

import com.nast.domain.entities.base.BaseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseEntityService<T extends BaseEntity> {

    T save(T target);

    T findOne(Long id);

    boolean exists(Long id);

    void delete(Long id);

    void deleteAll();

    T findOne(Predicate predicate);

    Page<T> findAll(Predicate predicate, Pageable pageable);
}
