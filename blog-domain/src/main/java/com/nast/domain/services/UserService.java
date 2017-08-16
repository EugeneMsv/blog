package com.nast.domain.services;

import com.nast.domain.entities.security.User;

import java.util.List;

public interface UserService {

    User findOne(Long id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Long id, String sso);
}

