package com.hzh.snails.connectknowledge.service;

import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse signUp(User user){
        int resultCount = userMapper.checkUserLogin(user.getUserLogin());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("该用户名已存在");
        }
        user.setUserStatus(1);
        resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功", user);
    }

    public String signIn(User user){
        return "index";
    }
}
