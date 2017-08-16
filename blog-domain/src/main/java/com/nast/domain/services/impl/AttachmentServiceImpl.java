package com.nast.domain.services.impl;

import com.nast.domain.entities.Attachment;
import com.nast.domain.repositories.PersistedEntityRepository;
import com.nast.domain.repositories.AttachmentRepository;
import com.nast.domain.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends AbstractCrudServiceImpl<Attachment> implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    protected PersistedEntityRepository<Attachment> getRepository() {
        return attachmentRepository;
    }
}
