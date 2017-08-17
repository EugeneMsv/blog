package com.nast.web.controllers;

import com.nast.domain.dto.TagDto;
import com.nast.domain.dto.mapper.EntityMapper;
import com.nast.domain.dto.mapper.TagMapper;
import com.nast.domain.entities.Tag;
import com.nast.domain.services.CrudService;
import com.nast.domain.services.TagService;
import com.nast.web.controllers.base.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagCrudController extends AbstractCrudController<Tag, TagDto> {

    private final TagService tagService;

    private final TagMapper tagMapper;

    @Autowired
    public TagCrudController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @Override
    protected CrudService<Tag> getCrudService() {
        return tagService;
    }

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }
}
