package com.nast.domain.repositories;

import com.nast.domain.entities.base.BaseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BaseEntityRepository<T extends BaseEntity>
        extends Repository<T, Long>, QueryDslPredicateExecutor<T> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */
    <S extends T> S save(S entity);


    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Returns whether any entity  exists.
     *
     * @return true if any entity  exists, {@literal false} otherwise
     */
    default boolean exists() {
        return count() > 0;
    }


    /**
     * Saves all given entities.
     *
     * @param entities
     * @return the saved entities
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    <S extends T> Iterable<S> save(Iterable<S> entities);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(Long id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    void delete(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * Returns an {@link Optional} of single entity matching the given {@link Long}.
     *
     * @param id can be {@literal null}.
     * @return an {@link Optional} of single entity matching the given {@link Long}.
     */
    Optional<T> findOne(Long id);

}
