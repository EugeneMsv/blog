package com.nast.domain.services.impl;

import com.nast.domain.entities.Post;
import com.nast.domain.filters.PostFilter;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.PostRepository;
import com.nast.domain.services.PostService;
import com.nast.domain.specifications.PostFilterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends FilteredEntityServiceImpl<Post, PostFilter> implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    protected BaseEntityRepository<Post> getRepository() {
        return postRepository;
    }

}
