package com.nast.web.controllers;

import com.nast.domain.dto.PostRegisterDto;
import com.nast.domain.dto.mapper.EntityMapper;
import com.nast.domain.dto.mapper.PostRegisterMapper;
import com.nast.domain.entities.PostRegister;
import com.nast.domain.services.CrudService;
import com.nast.domain.services.PostRegisterService;
import com.nast.web.controllers.base.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postRegister")
public class PostRegisterCrudController extends AbstractCrudController<PostRegister, PostRegisterDto> {

    private final PostRegisterService postRegisterService;

    private final PostRegisterMapper postRegisterMapper;

    @Autowired
    public PostRegisterCrudController(PostRegisterService postRegisterService,
                                      PostRegisterMapper postRegisterMapper) {
        this.postRegisterService = postRegisterService;
        this.postRegisterMapper = postRegisterMapper;
    }

    @Override
    protected CrudService<PostRegister> getCrudService() {
        return postRegisterService;
    }

    @Override
    protected EntityMapper<PostRegister, PostRegisterDto> getEntityMapper() {
        return postRegisterMapper;
    }
}
