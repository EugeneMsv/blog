package com.nast.domain.entities;

import com.nast.domain.entities.base.Identity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "attachment")
public class Attachment extends Identity {

    private static final long serialVersionUID = -8412370153231420105L;

    @Lob
    @Column(name = "file", length = 500000)
    private byte[] file;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && o instanceof Attachment && getId().equals(((Identity) o).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
