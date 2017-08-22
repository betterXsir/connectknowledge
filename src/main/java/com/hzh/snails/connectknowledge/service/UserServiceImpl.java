package com.hzh.snails.connectknowledge.service;

import com.google.common.io.Closer;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service("userService")
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    private static Closer closer = Closer.create();

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

    public ServerResponse signIn(User user){
        int resultCount = userMapper.checkUserLogin(user.getUserLogin());
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名错误");
        }
        User findUser = userMapper.checkIdentity(user.getUserLogin(), user.getUserPass());
        if(findUser == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        return ServerResponse.createBySuccess("登录成功", findUser);
    }

    public ServerResponse setAvatar(MultipartFile file, User user) throws IOException{
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
            closer.register(bais);
        } finally {
            closer.close();
        }
        // TODO: 2017/8/22 上传头像到七牛，存储该头像的url到数据库
        return ServerResponse.createBySuccessMessage("TEST");
    }
}
