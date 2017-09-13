package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class PostWithBLOBs extends Post {
    private String postContent;

    private String postTitle;

    private String postExcerpt="";

    public PostWithBLOBs(Long id, Long postAuthor, Date postDate, Date postModified, String postStatus, String commentStatus, String postType, String postMimeType, Integer postLabel, Integer postWatchs, Integer postLikes, Integer postComments, String postName, String postContent, String postTitle, String postExcerpt) {
        super(id, postAuthor, postDate, postModified, postStatus, commentStatus, postType, postMimeType, postLabel, postWatchs, postLikes, postComments, postName);
        this.postContent = postContent;
        this.postTitle = postTitle;
        this.postExcerpt = postExcerpt;
    }

    public PostWithBLOBs() {
        super();
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt == null ? null : postExcerpt.trim();
    }
}