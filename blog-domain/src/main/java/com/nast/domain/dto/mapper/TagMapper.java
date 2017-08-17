package com.nast.domain.dto.mapper;

import com.nast.domain.dto.TagDto;
import com.nast.domain.entities.Tag;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "tagMapper",
        withIgnoreFields = {"new"},
        withIgnoreNullValue = true)
public interface TagMapper extends EntityMapper<Tag, TagDto> {
}
