package com.nast.services.impl;

import com.nast.domain.entities.Commentary;
import com.nast.repositories.BaseEntityRepository;
import com.nast.repositories.CommentaryRepository;
import com.nast.services.CommentaryService;
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
    BaseEntityRepository<Commentary> getRepository() {
        return commentaryRepository;
    }
}
