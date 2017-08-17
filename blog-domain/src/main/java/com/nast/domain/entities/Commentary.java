package com.nast.domain.entities;

import com.nast.domain.entities.base.Identity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentary")
public class Commentary extends Identity {

    private static final long serialVersionUID = -2612882182166770506L;

    @Column(name = "author", nullable = false)
    private String author;

    // TODO: 17.08.2017 будет проблема с десериализацией в dto для jackson
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "message")
    private String message;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postRegister", nullable = false)
    private PostRegister postRegister;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PostRegister getPostRegister() {
        return postRegister;
    }

    public void setPostRegister(PostRegister postRegister) {
        this.postRegister = postRegister;
    }
}
