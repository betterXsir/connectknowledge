package com.hzh.snails.connectknowledge.service;

import com.hzh.snails.connectknowledge.common.Const;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.PostMapper;
import com.hzh.snails.connectknowledge.domain.PostWithBLOBs;
import com.hzh.snails.connectknowledge.utils.QiniuUploadImage;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

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
            putRet = QiniuUploadImage.getInstance().uploadPostImage(bais, "ckpostimages");
        }catch (QiniuException var3){
            Response r = var3.response;
            return ServerResponse.createByError(r.toString(),r.bodyString());
        }finally {
            bais.close();
        }
        if(putRet != null && putRet.key != null) {
            return ServerResponse.createBySuccess(Const.CKPOSTIMAGES_LINK + putRet.key);
        }
        return ServerResponse.createByErrorMessage("上传失败");
    }

    public ServerResponse writePost(PostWithBLOBs post){
        String postName = UUID.randomUUID().toString().replace("-","");
        post.setPostName(postName);
        System.out.println(post.getPostTitle() + "\n" + post.getCommentStatus());
        int resultCount = postMapper.insert(post);
        if(resultCount > 0){
            return ServerResponse.createBySuccess(post);
        }
        return ServerResponse.crateByError();
    }

    public ServerResponse updatePost(PostWithBLOBs post){
        int resultCount = postMapper.updatePostByName(post.getPostName(), post.getPostTitle(), post.getPostContent());
        if(resultCount > 0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("文章不存在");
    }
}
