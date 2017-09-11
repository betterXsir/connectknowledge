package com.hzh.snails.connectknowledge.dao;

import com.hzh.snails.connectknowledge.domain.Like;

public interface LikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
}