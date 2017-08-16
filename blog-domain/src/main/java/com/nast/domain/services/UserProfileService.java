package com.nast.domain.services;

import com.nast.domain.entities.security.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile findOne(Long id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
