package com.nast.domain.dto.mapper;

import com.nast.domain.dto.TagDto;
import com.nast.domain.entities.Tag;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "tagMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true)
public interface TagMapper extends EntityMapper<Tag, TagDto> {
}
