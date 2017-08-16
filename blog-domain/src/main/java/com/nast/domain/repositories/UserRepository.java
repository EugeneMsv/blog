package com.nast.domain.repositories;

import com.nast.domain.entities.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findBySsoId(String ssoId);

    void deleteBySsoId(String ssoId);

    List<User> findAll();
}
