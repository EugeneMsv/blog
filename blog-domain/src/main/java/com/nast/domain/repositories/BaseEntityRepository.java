package com.nast.domain.repositories;

import com.nast.domain.entities.base.BaseEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BaseEntityRepository<T extends BaseEntity>
        extends CrudRepository<T, Long>, QueryDslPredicateExecutor<T> {
}
