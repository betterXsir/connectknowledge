package com.hzh.snails.connectknowledge.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzh.snails.connectknowledge.common.Const;
import com.hzh.snails.connectknowledge.common.ResponseCode;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.domain.PostWithBLOBs;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public ServerResponse writePost(@ModelAttribute PostWithBLOBs post, HttpSession session){
        ServerResponse res = null;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            res = ServerResponse.createByErrorMessage("Need Login");
            res.setStatus(ResponseCode.NEED_LOGIN.getCode());
            return res;
        }
        post.setPostAuthor(user.getId());
        res = postService.writePost(post);
        if(res.isSuccess()){
            session.setAttribute(Const.CURRENT_POST, post);
        }
        return res;
    }

    @RequestMapping(value = "updatePost.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updatePost(@ModelAttribute PostWithBLOBs post, HttpSession session){
        ServerResponse res = null;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            res = ServerResponse.createByErrorMessage("Need Login");
            res.setStatus(ResponseCode.NEED_LOGIN.getCode());
            return res;
        }
        post.setPostAuthor(user.getId());
        res = postService.updatePost(post);
        return null;
    }

    @RequestMapping(value = "uploadImage.do")
    @ResponseBody
    public ServerResponse uploadImage(MultipartFile wmd_file_upload, HttpSession session){
        ServerResponse res = null;
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            res = ServerResponse.createByErrorMessage("Need Login");
            res.setStatus(ResponseCode.NEED_LOGIN.getCode());
            return res;
        }
        try {
            res = postService.uploadImage(wmd_file_upload);
        }catch (IOException var){
            res = ServerResponse.createByErrorMessage(var.getMessage());
        }
        return res;
    }
}
