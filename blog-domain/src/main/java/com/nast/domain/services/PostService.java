package com.nast.domain.services;

import com.nast.domain.entities.Post;
import com.nast.domain.filters.PostFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends BaseEntityService<Post> {

    Page<Post> findAll(PostFilter postFilter, Pageable pageable);

}
