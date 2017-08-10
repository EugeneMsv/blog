package com.nast.domain.services.impl;

import com.nast.domain.entities.security.PersistentLogin;
import com.nast.domain.repositories.PersistentLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class PersistentTokenServiceImpl implements PersistentTokenRepository {

    private final PersistentLoginRepository persistentLoginRepository;

    @Autowired
    public PersistentTokenServiceImpl(PersistentLoginRepository persistentLoginRepository) {
        this.persistentLoginRepository = persistentLoginRepository;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLastUsed(token.getDate());
        persistentLoginRepository.save(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = persistentLoginRepository.findOne(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        persistentLoginRepository.save(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogin persistentLogin = Optional.ofNullable(persistentLoginRepository.findOne(seriesId))
                .orElseThrow(IllegalArgumentException::new);
        return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                persistentLogin.getToken(), persistentLogin.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {
        Optional.ofNullable(persistentLoginRepository.findByUsername(username))
                .ifPresent((pL) -> persistentLoginRepository.delete(pL.getSeries()));
    }
}
