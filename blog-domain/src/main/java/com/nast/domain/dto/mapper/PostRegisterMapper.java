package com.nast.domain.dto.mapper;

import com.nast.domain.dto.PostRegisterDto;
import com.nast.domain.entities.PostRegister;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "postRegisterMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true,
        withCustomFields = {
                @Field(value = "post", withCustom = PostMapper.class)
        }
)
public interface PostRegisterMapper extends EntityMapper<PostRegister, PostRegisterDto> {

    @Override
    PostRegister mapToEntity(PostRegisterDto dto);

    @Override
    PostRegister updateEntityWithDto(PostRegisterDto dto, PostRegister entity);

    @Override
    PostRegisterDto mapToDto(PostRegister entity);
}
