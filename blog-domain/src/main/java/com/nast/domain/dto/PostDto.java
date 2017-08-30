package com.nast.domain.dto;

import com.nast.domain.dto.base.IdentityDto;

public class PostDto extends IdentityDto {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
