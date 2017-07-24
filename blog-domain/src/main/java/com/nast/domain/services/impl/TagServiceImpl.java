package com.nast.domain.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.TagRepository;
import com.nast.domain.services.TagService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl extends BaseEntityServiceImpl<Tag> implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected BaseEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
