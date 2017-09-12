package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.common.Const;
import com.hzh.snails.connectknowledge.common.ResponseCode;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.service.PostServiceImpl;
import com.hzh.snails.connectknowledge.utils.QiniuUploadAvatar;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Created by huzhenhua on 2017/9/11.
 */
@Controller
@RequestMapping("/post")
public class PostControl {
    @Autowired
    private PostServiceImpl postService;

    @RequestMapping(value="writer")
    public String getWriter(){
        return "post/writerEasy";
    }

    @RequestMapping(value = "writePost.do")
    @ResponseBody
    public ServerResponse writePost(String postTitle, String postContent){
        System.out.println(postTitle);
        System.out.println(postContent);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "uploadImage.do")
    @ResponseBody
    public ServerResponse uploadImage(MultipartFile file, HttpSession session){
        ServerResponse res = null;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            res = ServerResponse.createByErrorMessage("Need Login");
            res.setStatus(ResponseCode.NEED_LOGIN.getCode());
        }
        try {
            res = postService.uploadImage(file);
        }catch (IOException var){
            res = ServerResponse.createByErrorMessage(var.getMessage());
        }
        return res;
    }
}
