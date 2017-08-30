package com.nast.domain.dto.mapper;

import com.nast.domain.dto.base.IdentityDto;
import com.nast.domain.entities.base.Identity;

/**
 * Base interface for all mappers between Dto and domain entities
 *
 * @param <E> entity type
 * @param <D> dto type
 */
public interface EntityMapper<E extends Identity, D extends IdentityDto> {

    E mapToEntity(D dto);

    E updateEntityWithDto(D dto, E entity);

    D mapToDto(E entity);
}
