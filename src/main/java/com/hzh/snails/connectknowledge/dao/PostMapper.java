package com.hzh.snails.connectknowledge.dao;

import com.hzh.snails.connectknowledge.domain.Post;
import com.hzh.snails.connectknowledge.domain.PostWithBLOBs;

public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PostWithBLOBs record);

    int insertSelective(PostWithBLOBs record);

    PostWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PostWithBLOBs record);

    int updateByPrimaryKey(Post record);
}