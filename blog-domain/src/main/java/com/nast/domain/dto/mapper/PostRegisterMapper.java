package com.nast.domain.dto.mapper;

import com.nast.domain.dto.PostRegisterDto;
import com.nast.domain.dto.TagDto;
import com.nast.domain.entities.PostRegister;
import com.nast.domain.entities.Tag;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "postRegister",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true)
public interface PostRegisterMapper extends EntityMapper<PostRegister, PostRegisterDto> {
}
