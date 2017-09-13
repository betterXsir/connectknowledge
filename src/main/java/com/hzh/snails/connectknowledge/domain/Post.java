package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class Post {
    private Long id;

    private Long postAuthor;

    private Date postDate;

    private Date postModified;

    private String postStatus="publish";

    private String commentStatus="open";

    private String postType="post";

    private String postMimeType="";

    private Integer postLabel=0;

    private Integer postWatchs=0;

    private Integer postLikes=0;

    private Integer postComments=0;

    private String postName;

    public Post(Long id, Long postAuthor, Date postDate, Date postModified, String postStatus, String commentStatus, String postType, String postMimeType, Integer postLabel, Integer postWatchs, Integer postLikes, Integer postComments, String postName) {
        this.id = id;
        this.postAuthor = postAuthor;
        this.postDate = postDate;
        this.postModified = postModified;
        this.postStatus = postStatus;
        this.commentStatus = commentStatus;
        this.postType = postType;
        this.postMimeType = postMimeType;
        this.postLabel = postLabel;
        this.postWatchs = postWatchs;
        this.postLikes = postLikes;
        this.postComments = postComments;
        this.postName = postName;
    }

    public Post() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPostModified() {
        return postModified;
    }

    public void setPostModified(Date postModified) {
        this.postModified = postModified;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus == null ? null : postStatus.trim();
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus == null ? null : commentStatus.trim();
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType == null ? null : postType.trim();
    }

    public String getPostMimeType() {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType == null ? null : postMimeType.trim();
    }

    public Integer getPostLabel() {
        return postLabel;
    }

    public void setPostLabel(Integer postLabel) {
        this.postLabel = postLabel;
    }

    public Integer getPostWatchs() {
        return postWatchs;
    }

    public void setPostWatchs(Integer postWatchs) {
        this.postWatchs = postWatchs;
    }

    public Integer getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Integer postLikes) {
        this.postLikes = postLikes;
    }

    public Integer getPostComments() {
        return postComments;
    }

    public void setPostComments(Integer postComments) {
        this.postComments = postComments;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

}