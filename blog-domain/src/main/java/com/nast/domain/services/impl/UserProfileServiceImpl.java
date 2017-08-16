package com.nast.domain.services.impl;

import com.nast.domain.entities.security.UserProfile;
import com.nast.domain.entities.security.UserProfileType;
import com.nast.domain.profiling.Profiling;
import com.nast.domain.repositories.UserProfileRepository;
import com.nast.domain.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Profiling(showArgs = true, showOutput = true)
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserProfile findOne(Long id) {
        return userProfileRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserProfile findByType(String type) {
        return userProfileRepository.findByType(UserProfileType.valueOf(type));

    }

    @Transactional(readOnly = true)
    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }
}
