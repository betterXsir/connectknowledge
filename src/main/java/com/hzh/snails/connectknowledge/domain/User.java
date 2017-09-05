package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class User {
    private Long id;

    private String userLogin;

    private String userPass;

    private String userNickname;

    private String userEmail;

    private Date userRegistered;

    private Integer userStatus;

    private String userAvatar;

    public User(Long id, String userLogin, String userPass, String userNickname, String userEmail, Date userRegistered, Integer userStatus, String userAvatar) {
        this.id = id;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userRegistered = userRegistered;
        this.userStatus = userStatus;
        this.userAvatar = userAvatar;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin == null ? null : userLogin.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}