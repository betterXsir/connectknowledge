package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class Like {
    private Long id;

    private Long likeAuthor;

    private Long likePost;

    private Long likeComment;

    private Date likeDate;

    public Like(Long id, Long likeAuthor, Long likePost, Long likeComment, Date likeDate) {
        this.id = id;
        this.likeAuthor = likeAuthor;
        this.likePost = likePost;
        this.likeComment = likeComment;
        this.likeDate = likeDate;
    }

    public Like() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikeAuthor() {
        return likeAuthor;
    }

    public void setLikeAuthor(Long likeAuthor) {
        this.likeAuthor = likeAuthor;
    }

    public Long getLikePost() {
        return likePost;
    }

    public void setLikePost(Long likePost) {
        this.likePost = likePost;
    }

    public Long getLikeComment() {
        return likeComment;
    }

    public void setLikeComment(Long likeComment) {
        this.likeComment = likeComment;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}