package com.nast.repositories;

import com.nast.domain.entities.Tag;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag,Long>, QueryDslPredicateExecutor<Tag> {
}
