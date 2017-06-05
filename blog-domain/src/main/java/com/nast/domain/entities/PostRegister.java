package com.nast.domain.entities;

import com.nast.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PostRegister extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "register")
    private Post post;

    private Long likes;

    private Long views;

    private Long commentsNum;

    private LocalDateTime createdTime;

    private String metaInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tags", joinColumns = @JoinColumn(name = "tags", referencedColumnName = "id"))
    private List<Commentary> commentaries;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Long commentsNum) {
        this.commentsNum = commentsNum;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }
}
