package com.nast.domain.services;

import com.nast.domain.entities.Commentary;
import com.nast.domain.entities.PostRegister;
import com.nast.domain.filters.PostRegisterFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRegisterService extends FilteredEntityService<PostRegister, PostRegisterFilter> {

    Commentary addToCommentary(Long postRegisterId, Commentary commentary);

    void removeCommentary(Long postRegisterId, Long commentaryId);

    Page<Commentary> findAllCommentaries(Long postRegisterId, Pageable pageable);
}
