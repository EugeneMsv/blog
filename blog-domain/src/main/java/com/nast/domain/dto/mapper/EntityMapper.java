package com.nast.domain.dto.mapper;

import com.nast.domain.dto.base.IdentityDto;
import com.nast.domain.entities.base.Identity;
import fr.xebia.extras.selma.Mapper;

/**
 * Base interface for all mappers between Dto and domain entities
 *
 * @param <E> entity type
 * @param <D> dto type
 */
public interface EntityMapper<E extends Identity, D extends IdentityDto> {

    E mapToEntity(D dto);

    E updateEntityWithDto(E entity, D dto);

    D mapToDto(E entity);
}
