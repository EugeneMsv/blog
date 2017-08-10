package com.nast.domain.repositories;

import com.nast.domain.entities.PersistentLogin;
import org.springframework.data.repository.CrudRepository;

public interface PersistentLoginRepository extends CrudRepository<PersistentLogin, String> {

    PersistentLogin findByUsername(String username);
}
