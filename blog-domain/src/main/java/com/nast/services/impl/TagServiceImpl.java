package com.nast.services.impl;

import com.nast.domain.entities.Tag;
import com.nast.repositories.TagRepository;
import com.nast.services.TagService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag save(Tag target) {
        return tagRepository.save(target);
    }

    @Override
    public Tag findOne(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag findOne(Predicate predicate) {
        return tagRepository.findOne(predicate);
    }

    @Override
    public boolean exists(Long id) {
        return tagRepository.exists(id);
    }

    @Override
    public void delete(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        tagRepository.deleteAll();
    }

    @Override
    public Page<Tag> findAll(Predicate predicate, Pageable pageable) {
        return tagRepository.findAll(predicate, pageable);
    }
}
