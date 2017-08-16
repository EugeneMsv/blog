package com.nast.domain.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.domain.filters.TagFilter;
import com.nast.domain.profiling.Profiling;
import com.nast.domain.repositories.PersistedEntityRepository;
import com.nast.domain.repositories.TagRepository;
import com.nast.domain.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Profiling(showArgs = true, timeRecord = true, showOutput = true)
@Service
public class TagServiceImpl extends FilteredEntityServiceImpl<Tag, TagFilter> implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected PersistedEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
