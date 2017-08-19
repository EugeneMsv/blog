package com.nast.domain.entities;

import com.nast.domain.entities.base.Identity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post_register")
public class PostRegister extends Identity {

    private static final long serialVersionUID = -1207026720655304124L;

    /**
     * Постр регистр является владельцем ссылки на пост
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Post post;

    @Column(name = "preview")
    private String preview;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "views")
    private Long views;

    @Column(name = "comments_num")
    private Long commentsNum;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Lob
    @Column(name = "meta_info", columnDefinition = "TEXT")
    private String metaInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentary> commentaries;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && o instanceof PostRegister && getId().equals(((Identity) o).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
