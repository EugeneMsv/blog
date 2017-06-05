package com.nast.domain.entities;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tag extends BaseEntity {

    @Column(unique = true, nullable = false, length = 20)
    private String code;

    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
