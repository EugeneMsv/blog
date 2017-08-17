package com.nast.web.controllers;

import com.nast.domain.dto.TagDto;
import com.nast.domain.dto.mapper.EntityMapper;
import com.nast.domain.dto.mapper.TagMapper;
import com.nast.domain.entities.Tag;
import com.nast.domain.filters.TagFilter;
import com.nast.domain.services.FilteredEntityService;
import com.nast.domain.services.TagService;
import com.nast.web.controllers.base.AbstractFilterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag/filter")
public class TagFilterController extends AbstractFilterController<Tag, TagDto, TagFilter> {

    private final TagService tagService;

    private final TagMapper tagMapper;

    @Autowired
    public TagFilterController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @Override
    protected FilteredEntityService<Tag, TagFilter> getFilteredEntityService() {
        return tagService;
    }

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }
}
