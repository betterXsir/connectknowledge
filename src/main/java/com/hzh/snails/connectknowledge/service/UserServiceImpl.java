package com.hzh.snails.connectknowledge.service;

import com.google.common.io.Closer;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.utils.QiniuUploadImage;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
        user.setUserAvatar("default.jpeg");
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

        // TODO: 2017/8/22 上传头像到七牛，存储该头像的url到数据库
        String filename = user.getUserLogin();
        if(file.getSize() == 0){
            return ServerResponse.createByErrorMessage("空文件");
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
        DefaultPutRet putRet = null;
        try {
            putRet = QiniuUploadImage.getInstance().uploadAvatar(user.getUserAvatar(), bais);
        }catch (QiniuException var3){
            Response r = var3.response;
            return ServerResponse.createByError(r.toString(),r.bodyString());
        }finally {
            bais.close();
        }
        if(putRet != null && putRet.key != null) {
            user.setUserAvatar(putRet.key);
            userMapper.updateByPrimaryKey(user);
            return ServerResponse.createBySuccess(putRet.key);
        }
        return ServerResponse.createByErrorMessage("上传失败");
    }

    public ServerResponse setInfo(long ID, String userEmail, String userNickname){
        int resultCount = userMapper.updateInformation(ID, userEmail, userNickname);
        if(resultCount > 0){
            return ServerResponse.createBySuccess();
        }
        else
            return ServerResponse.crateByError();
    }
}
