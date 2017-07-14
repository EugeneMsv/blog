package com.nast.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.repositories.BaseEntityRepository;
import com.nast.repositories.TagRepository;
import com.nast.services.TagService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl extends BaseEntityServiceImpl<Tag> implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    BaseEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
