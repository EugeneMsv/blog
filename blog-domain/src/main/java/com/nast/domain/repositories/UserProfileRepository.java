package com.nast.domain.repositories;

import com.nast.domain.entities.security.UserProfile;
import com.nast.domain.entities.security.UserProfileType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    UserProfile findByType(UserProfileType type);

    List<UserProfile> findAll();
}
