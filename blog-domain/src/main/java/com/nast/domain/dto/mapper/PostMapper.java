package com.nast.domain.dto.mapper;

import com.nast.domain.dto.PostDto;
import com.nast.domain.entities.Post;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "postMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true,
        withCustomFields = {
                @Field(value = "postRegister", withCustom = PostRegisterMapper.class)
        }
)
public interface PostMapper extends EntityMapper<Post, PostDto> {

    @Override
    Post mapToEntity(PostDto dto);

    @Override
    Post updateEntityWithDto(PostDto dto, Post entity);

    @Override
    PostDto mapToDto(Post entity);
}
