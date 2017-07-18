package com.nast.domain.services.impl;

import com.nast.domain.entities.Post;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.PostRepository;
import com.nast.domain.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends BaseEntityServiceImpl<Post> implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    BaseEntityRepository<Post> getRepository() {
        return postRepository;
    }
}
