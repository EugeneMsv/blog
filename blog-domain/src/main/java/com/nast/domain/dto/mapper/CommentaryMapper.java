package com.nast.domain.dto.mapper;

import com.nast.domain.dto.CommentaryDto;
import com.nast.domain.entities.Commentary;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "commentaryMapper",
        withIgnoreFields = {"new", "postregister"},
        withIgnoreNullValue = true)
public interface CommentaryMapper extends EntityMapper<Commentary, CommentaryDto> {
}
