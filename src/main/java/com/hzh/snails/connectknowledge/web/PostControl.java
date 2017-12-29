package com.hzh.snails.connectknowledge.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzh.snails.connectknowledge.common.Const;
import com.hzh.snails.connectknowledge.common.ResponseCode;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.domain.PostWithBLOBs;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.service.PostServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value="falconDingDing")
    @ResponseBody
    public ServerResponse falconDingDing(@RequestParam String content){
        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=40042c2ab93ef6bd713501f022f90f20d726f05a25452657be97b52f8a1d586c";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(WEBHOOK_TOKEN);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        String textMsg = "{\"msgtype\":\"text\",\"text\":{\"content\":\"test\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httpPost.setEntity(se);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                return ServerResponse.createBySuccessMessage(result);
            }
            return ServerResponse.createByErrorMessage("Connect Error");
        }catch (IOException var){
            return ServerResponse.createByErrorMessage("IO Error");
        }
    }

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
