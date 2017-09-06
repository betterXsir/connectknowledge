package com.hzh.snails.connectknowledge.utils;

import com.google.gson.Gson;
import com.hzh.snails.connectknowledge.common.QiniuAccessSecret;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class QiniuUploadAvatar {

    public static DefaultPutRet uploadAvatar(String filename, ByteArrayInputStream bais, String bucketName) throws QiniuException{
        Auth auth = Auth.create(QiniuAccessSecret.ACCESS_KEY, QiniuAccessSecret.SERCRET_KEY);
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String key = null;
        if(!filename.equals("default.jpeg")){
            try {
                bucketManager.delete(bucketName, filename);
            } catch (QiniuException var) {
                var.printStackTrace();
                throw var;
            }
        }
        String upToken = auth.uploadToken(bucketName,key);
        Response response = uploadManager.put(bais, key, upToken, null, null);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet;
    }
}
