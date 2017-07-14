package com.nast.services.impl;

import com.nast.domain.entities.PostRegister;
import com.nast.repositories.BaseEntityRepository;
import com.nast.repositories.PostRegisterRepository;
import com.nast.services.PostRegisterService;
import com.nast.services.PostService;
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
    BaseEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }
}
