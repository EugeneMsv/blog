package com.nast.domain.services.impl;

import com.nast.domain.entities.Commentary;
import com.nast.domain.entities.PostRegister;
import com.nast.domain.filters.PostRegisterFilter;
import com.nast.domain.repositories.PersistedEntityRepository;
import com.nast.domain.repositories.PostRegisterRepository;
import com.nast.domain.services.CommentaryManager;
import com.nast.domain.services.PostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostRegisterServiceImpl extends FilteredEntityServiceImpl<PostRegister, PostRegisterFilter>
        implements PostRegisterService {

    private final PostRegisterRepository postRegisterRepository;

    private final CommentaryManager commentaryManager;

    @Autowired
    public PostRegisterServiceImpl(PostRegisterRepository postRegisterRepository,
                                   CommentaryManager commentaryManager) {
        this.postRegisterRepository = postRegisterRepository;
        this.commentaryManager = commentaryManager;
    }

    @Override
    protected PersistedEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }


    @Override
    public Commentary addToCommentary(Long postRegisterId, Commentary commentary) {
        return commentaryManager.addToPostRegister(postRegisterId, commentary);
    }

    @Override
    public void removeCommentary(Long postRegisterId, Long commentaryId) {
        commentaryManager.removeFromPostRegister(postRegisterId, commentaryId);
    }

    @Override
    public Page<Commentary> findAllCommentaries(Long postRegisterId, Pageable pageable) {
        return commentaryManager.findAllFromPostRegister(postRegisterId, pageable);
    }
}
