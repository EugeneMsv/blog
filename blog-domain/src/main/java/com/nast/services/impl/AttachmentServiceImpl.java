package com.nast.services.impl;

import com.nast.domain.entities.Attachment;
import com.nast.repositories.BaseEntityRepository;
import com.nast.repositories.AttachmentRepository;
import com.nast.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends BaseEntityServiceImpl<Attachment> implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    BaseEntityRepository<Attachment> getRepository() {
        return attachmentRepository;
    }
}
