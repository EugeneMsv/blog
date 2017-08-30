package com.nast.web.controllers.base;

import com.nast.domain.dto.base.IdentityDto;
import com.nast.domain.dto.mapper.EntityMapper;
import com.nast.domain.entities.base.Identity;
import com.nast.domain.profiling.Profiling;
import com.nast.domain.services.CrudService;
import com.nast.web.controllers.utils.ResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Profiling(showArgs = true, timeRecord = true, showOutput = true)
public abstract class AbstractCrudController<E extends Identity, D extends IdentityDto> {

    protected ResponseUtils responseUtils = ResponseUtils.getInstance();

    protected abstract CrudService<E> getCrudService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    private E toEntity(D dto) {
        return getEntityMapper().mapToEntity(dto);
    }

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    private E updateEntityByDto(E entity, D dto) {
        return getEntityMapper().updateEntityWithDto(dto, entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<D> save(@RequestBody D request) {
        E entity = Optional.ofNullable(request)
                .map(this::toEntity)
                .orElseThrow(() -> new IllegalArgumentException("Input for save is null"));
        E result = getCrudService().save(entity);
        return responseUtils.created(toDto(result));
    }


    @PutMapping(value = {"{id}"},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody D request) {
        checkDtoForUpdate(request);
        request.setId(id);
        E entity;
        if (id == null) {
            entity = getCrudService().save(toEntity(request));
        } else {
            Optional<E> founded = getCrudService().findOne(id);
            if (founded.isPresent()) {
                E originEntity = updateEntityByDto(founded.get(), request);
                entity = getCrudService().save(entityPreUpdateAction(originEntity));
            } else {
                entity = getCrudService().save(toEntity(request));
            }
        }
        return responseUtils.ok(toDto(entity));
    }


    @DeleteMapping(value = {"{id}"},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity remove(@PathVariable Long id) {
        getCrudService().delete(id);
        return responseUtils.noContent();
    }


    @GetMapping(value = {"{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<D> get(@PathVariable Long id) {
        Optional<E> founded = getCrudService().findOne(id);
        return founded.map((e) -> responseUtils.ok(toDto(e)))
                .orElse(responseUtils.notFound());
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<D>> findAll(Pageable pageable) {
        Page<E> entityPage = this.getCrudService().findAll(pageable);
        Page<D> dtos = entityPage.map(this::toDto);
        return responseUtils.ok(dtos);
    }

    protected void checkDtoForUpdate(D dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Input is null");
        }
    }

    protected E entityPreUpdateAction(E updatedEntity) {
        return updatedEntity;
    }
}
