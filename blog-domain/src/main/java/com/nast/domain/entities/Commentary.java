package com.nast.domain.entities;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Commentary extends BaseEntity{

    private String author;

    private LocalDateTime createdTime;

    private String message;

    private String email;

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
}