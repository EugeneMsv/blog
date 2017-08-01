package com.nast.domain.services.impl;

import com.nast.domain.entities.base.BaseEntity;
import com.nast.domain.entities.base.QBaseEntity;
import com.nast.domain.repositories.BaseEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// TODO: 24.07.2017 тестировать
public abstract class BaseEntityServiceImpl<E extends BaseEntity> {

    protected abstract BaseEntityRepository<E> getRepository();

    @Transactional
    public E save(E target) {
        return getRepository().save(target);
    }

    @Transactional(readOnly = true)
    public boolean exists() {
        return getRepository().exists();
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
    public Optional<E> findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return getRepository().findAll(QBaseEntity.baseEntity.isNotNull(), pageable);
    }
}
