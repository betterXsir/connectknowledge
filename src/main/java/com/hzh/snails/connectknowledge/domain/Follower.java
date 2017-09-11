package com.hzh.snails.connectknowledge.domain;

import java.util.Date;

public class Follower {
    private Long id;

    private Long userFan;

    private Long userFollower;

    private Date followerDate;

    private String followerStatus;

    public Follower(Long id, Long userFan, Long userFollower, Date followerDate, String followerStatus) {
        this.id = id;
        this.userFan = userFan;
        this.userFollower = userFollower;
        this.followerDate = followerDate;
        this.followerStatus = followerStatus;
    }

    public Follower() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserFan() {
        return userFan;
    }

    public void setUserFan(Long userFan) {
        this.userFan = userFan;
    }

    public Long getUserFollower() {
        return userFollower;
    }

    public void setUserFollower(Long userFollower) {
        this.userFollower = userFollower;
    }

    public Date getFollowerDate() {
        return followerDate;
    }

    public void setFollowerDate(Date followerDate) {
        this.followerDate = followerDate;
    }

    public String getFollowerStatus() {
        return followerStatus;
    }

    public void setFollowerStatus(String followerStatus) {
        this.followerStatus = followerStatus == null ? null : followerStatus.trim();
    }
}