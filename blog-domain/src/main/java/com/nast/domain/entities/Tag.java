package com.nast.domain.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class Tag extends BaseEntity {

    private String groupCode;

    private String description;

    @Basic
    @Column(nullable = false)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Basic
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "groupCode='" + groupCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
