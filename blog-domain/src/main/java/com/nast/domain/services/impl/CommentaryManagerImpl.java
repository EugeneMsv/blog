package com.nast.domain.services.impl;

import com.nast.domain.entities.Commentary;
import com.nast.domain.repositories.PersistedEntityRepository;
import com.nast.domain.repositories.CommentaryRepository;
import com.nast.domain.services.CommentaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentaryManagerImpl extends AbstractCrudServiceImpl<Commentary> implements CommentaryManager {

    private final CommentaryRepository commentaryRepository;

    @Autowired
    public CommentaryManagerImpl(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    protected PersistedEntityRepository<Commentary> getRepository() {
        return commentaryRepository;
    }

    @Override
    public Commentary addToPostRegister(Long postRegisterId, Commentary commentary) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFromPostRegister(Long postRegisterId, Long commentaryId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Commentary> findAllFromPostRegister(Long postRegisterId, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
