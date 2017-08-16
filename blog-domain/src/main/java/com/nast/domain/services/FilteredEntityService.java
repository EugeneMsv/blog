package com.nast.domain.services;

import com.nast.domain.entities.base.PersistedEntity;
import com.nast.domain.filters.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service for persist entities with capability for filtering
 *
 * @param <T> entity type
 * @param <F> filter type
 */
public interface FilteredEntityService<T extends PersistedEntity, F extends Filter> extends BaseEntityService<T> {

    /**
     * Check for existence in persist storage by filter
     *
     * @param filter search condition
     * @return true if exist false if not
     */
    boolean exists(F filter);

    /**
     * Find entity by filter
     *
     * @param filter search condition
     * @return optional of entity, empty if there is no such entity
     */
    Optional<T> findOne(F filter);

    /**
     * Find all entities by filter with given page
     *
     * @param filter   search condition
     * @param pageable page descriptor
     * @return page corresponding for pageable and filter
     */
    Page<T> findAll(F filter, Pageable pageable);

}
