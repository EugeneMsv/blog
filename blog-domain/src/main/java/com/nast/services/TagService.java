package com.nast.services;

import com.nast.domain.entities.Tag;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    Tag save(Tag target);

    Tag findOne(Long id);

    boolean exists(Long id);

    void delete(Long id);

    void deleteAll();

    Tag findOne(Predicate predicate);

    Page<Tag> findAll(Predicate predicate, Pageable pageable);
}
