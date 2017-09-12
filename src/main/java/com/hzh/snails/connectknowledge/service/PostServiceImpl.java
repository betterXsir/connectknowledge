package com.hzh.snails.connectknowledge.service;

import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.PostMapper;
import com.hzh.snails.connectknowledge.utils.QiniuUploadAvatar;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by huzhenhua on 2017/9/12.
 */
@Service("postService")
public class PostServiceImpl {
    @Autowired
    private PostMapper postMapper;

    public ServerResponse uploadImage(MultipartFile file) throws IOException{
        if(file.getSize() == 0){
            return ServerResponse.createByErrorMessage("空文件");
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
        DefaultPutRet putRet = null;
        try {
            putRet = QiniuUploadAvatar.uploadAvatar("example", bais, "connectknowledge");
        }catch (QiniuException var3){
            Response r = var3.response;
            return ServerResponse.createByError(r.toString(),r.bodyString());
        }finally {
            bais.close();
        }
        if(putRet != null && putRet.key != null) {
            return ServerResponse.createBySuccess(putRet.key);
        }
        return ServerResponse.createByErrorMessage("上传失败");
    }
}
