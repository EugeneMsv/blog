package com.nast.web.controllers.base;

import com.nast.domain.dto.base.IdentityDto;
import com.nast.domain.dto.mapper.EntityMapper;
import com.nast.domain.entities.base.Identity;
import com.nast.domain.filters.Filter;
import com.nast.domain.profiling.Profiling;
import com.nast.domain.services.FilteredEntityService;
import com.nast.web.controllers.utils.ResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Profiling(showArgs = true, timeRecord = true, showOutput = true)
public abstract class AbstractFilterController<E extends Identity, D extends IdentityDto, F extends Filter> {

    protected ResponseUtils responseUtils = ResponseUtils.getInstance();

    protected abstract FilteredEntityService<E, F> getFilteredEntityService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<D>> filter(Pageable pageable, F filter) {
        Page<E> entityPage = this.getFilteredEntityService().findAll(filter, pageable);
        Page<D> dtos = entityPage.map(this::toDto);
        return responseUtils.ok(dtos);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> filter(F filter) {
        boolean exists = this.getFilteredEntityService().exists(filter);
        return exists ? responseUtils.ok() : responseUtils.notFound();
    }
}
