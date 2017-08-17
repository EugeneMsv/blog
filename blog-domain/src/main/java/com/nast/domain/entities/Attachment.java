package com.nast.domain.entities;

import com.nast.domain.entities.base.Identity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Attachment extends Identity {

    private static final long serialVersionUID = -8412370153231420105L;

    @Lob
    @Column(length = 500000)
    private byte[] file;

    private String description;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
