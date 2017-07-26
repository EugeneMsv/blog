package com.nast.domain.entities;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    private static final long serialVersionUID = -8786471757326098213L;

    @Column(unique = true, length = 100)
    private String code;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String text;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    @Column(nullable = false)
    private String author;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostState state;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "post")
    private PostRegister postRegister;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }
}
