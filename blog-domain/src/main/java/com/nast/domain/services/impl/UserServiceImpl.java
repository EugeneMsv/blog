package com.nast.domain.services.impl;

import com.nast.domain.entities.security.User;
import com.nast.domain.profiling.Profiling;
import com.nast.domain.repositories.UserRepository;
import com.nast.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Profiling(showArgs = true, showOutput = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findBySSO(String sso) {
        return userRepository.findBySsoId(sso);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUserBySSO(String sso) {
        User user = userRepository.findBySsoId(sso);
        Optional.of(user).ifPresent(userRepository::delete);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUserSSOUnique(Long id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (Objects.equals(user.getId(), id))));
    }
}
