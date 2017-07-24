package com.nast.domain.services.impl;

import com.nast.domain.entities.Commentary;
import com.nast.domain.repositories.BaseEntityRepository;
import com.nast.domain.repositories.CommentaryRepository;
import com.nast.domain.services.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaryServiceImpl extends BaseEntityServiceImpl<Commentary> implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    @Autowired
    public CommentaryServiceImpl(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    protected BaseEntityRepository<Commentary> getRepository() {
        return commentaryRepository;
    }
}
