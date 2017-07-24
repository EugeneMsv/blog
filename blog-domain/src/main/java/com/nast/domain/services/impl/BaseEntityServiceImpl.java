package com.nast.domain.services.impl;

import com.nast.domain.entities.base.BaseEntity;
import com.nast.domain.entities.base.QBaseEntity;
import com.nast.domain.filters.Filter;
import com.nast.domain.repositories.BaseEntityRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
// TODO: 24.07.2017 тестировать 
public abstract class BaseEntityServiceImpl<T extends BaseEntity> {

    protected abstract BaseEntityRepository<T> getRepository();

    @Autowired
    protected ConversionService conversionService;

    private Predicate convert(Filter filter) {
        return conversionService.convert(filter, Predicate.class);
    }

    @Transactional
    public T save(T target) {
        return getRepository().save(target);
    }

    @Transactional(readOnly = true)
    public boolean exists(Filter filter) {
        return getRepository().exists(convert(filter));
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
    public Optional<T> findOne(Filter filter) {
        return Optional.ofNullable(getRepository().findOne(convert(filter)));
    }

    @Transactional(readOnly = true)
    public Optional<T> findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Filter filter, Pageable pageable) {
        return getRepository().findAll(convert(filter), pageable);
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(QBaseEntity.baseEntity.isNotNull(), pageable);
    }
}
