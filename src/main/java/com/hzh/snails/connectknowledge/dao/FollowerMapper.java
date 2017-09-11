package com.hzh.snails.connectknowledge.dao;

import com.hzh.snails.connectknowledge.domain.Follower;

public interface FollowerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Follower record);

    int insertSelective(Follower record);

    Follower selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Follower record);

    int updateByPrimaryKey(Follower record);
}