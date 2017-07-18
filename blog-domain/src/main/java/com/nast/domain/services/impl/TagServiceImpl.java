package com.nast.domain.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.TagRepository;
import com.nast.domain.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
