package com.nast.domain.entities;

import javax.persistence.*;

public class Post extends BaseEntity {

    private String code;

    private String title;

    private String text;

    private Tag tag;

    private String author;

    private Attachment attachment;

    private PostState state;

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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "attachment", joinColumns = @JoinColumn(name = "attachment", referencedColumnName = "id"))
    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Enumerated(EnumType.STRING)
    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }
}
