package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class Comment {
    private Long id;

    private Long commentAuthor;

    private Long commentPost;

    private Date commentDate;

    private Long commentParent;

    private Integer commentLikes;

    private String commentContent;

    public Comment(Long id, Long commentAuthor, Long commentPost, Date commentDate, Long commentParent, Integer commentLikes, String commentContent) {
        this.id = id;
        this.commentAuthor = commentAuthor;
        this.commentPost = commentPost;
        this.commentDate = commentDate;
        this.commentParent = commentParent;
        this.commentLikes = commentLikes;
        this.commentContent = commentContent;
    }

    public Comment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(Long commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public Long getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(Long commentPost) {
        this.commentPost = commentPost;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Long getCommentParent() {
        return commentParent;
    }

    public void setCommentParent(Long commentParent) {
        this.commentParent = commentParent;
    }

    public Integer getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Integer commentLikes) {
        this.commentLikes = commentLikes;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }
}