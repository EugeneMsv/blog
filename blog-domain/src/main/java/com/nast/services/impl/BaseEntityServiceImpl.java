package com.nast.services.impl;

import com.nast.domain.entities.base.BaseEntity;
import com.nast.repositories.BaseEntityRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseEntityServiceImpl<T extends BaseEntity> {

    abstract BaseEntityRepository<T> getRepository();

    @Transactional
    public T save(T target) {
        return getRepository().save(target);
    }

    @Transactional(readOnly = true)
    public T findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional(readOnly = true)
    public T findOne(Predicate predicate) {
        return getRepository().findOne(predicate);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return getRepository().exists(id);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return getRepository().findAll(predicate, pageable);
    }
}
