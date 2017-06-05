package com.nast.domain.entities;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Column(unique = true, length = 100)
    private String code;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT NO NULL")
    private String text;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tags", joinColumns = @JoinColumn(name = "tags", referencedColumnName = "id"))
    private List<Tag> tags;

    @Column(nullable = false, length = 128)
    private String author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "attachment", joinColumns = @JoinColumn(name = "attachments", referencedColumnName = "id"))
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    private PostState state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true, nullable = false)
    private PostRegister register;

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
