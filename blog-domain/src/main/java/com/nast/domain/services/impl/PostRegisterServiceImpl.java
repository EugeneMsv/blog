package com.nast.domain.services.impl;

import com.nast.domain.entities.PostRegister;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.PostRegisterRepository;
import com.nast.domain.services.PostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRegisterServiceImpl extends BaseEntityServiceImpl<PostRegister> implements PostRegisterService {

    private final PostRegisterRepository postRegisterRepository;

    @Autowired
    public PostRegisterServiceImpl(PostRegisterRepository postRegisterRepository) {
        this.postRegisterRepository = postRegisterRepository;
    }

    @Override
    protected BaseEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }
}