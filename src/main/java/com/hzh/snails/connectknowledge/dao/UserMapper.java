package com.hzh.snails.connectknowledge.dao;

import com.hzh.snails.connectknowledge.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int checkUserLogin(String userLogin);

    User checkIdentity(@Param("userLogin") String userLogin, @Param("userPass") String userPass);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateInformation(@Param("id") long ID, @Param("userEmail") String userEmail, @Param("userNickname") String userNickname);
}