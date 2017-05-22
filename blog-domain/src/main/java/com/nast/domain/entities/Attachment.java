package com.nast.domain.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Lob;

public class Attachment extends BaseEntity {

    private byte[] file;

    private String description;

    @Lob
    @Column(length=500000)
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Basic
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
