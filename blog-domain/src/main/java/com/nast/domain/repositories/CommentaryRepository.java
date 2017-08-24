package com.nast.domain.repositories;

import com.nast.domain.entities.Commentary;

import java.util.Optional;

public interface CommentaryRepository extends PersistedEntityRepository<Commentary> {

    Optional<Commentary> findByPostRegisterIdAndId(Long postRegisterId, Long id);
}
