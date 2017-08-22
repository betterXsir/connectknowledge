package com.hzh.snails.connectknowledge.utils;

import com.google.gson.Gson;
import com.hzh.snails.connectknowledge.common.QiniuAccessSecret;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class QiniuUploadAvatar {
    private static final String filePrefix = "image/avatar/";

    public static boolean uploadAvatar(String filename, ByteArrayInputStream bais, String bucketName) throws QiniuException{
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = filePrefix + filename;
        Auth auth = Auth.create(QiniuAccessSecret.ACCESS_KEY, QiniuAccessSecret.SERCRET_KEY);
        String upToken = auth.uploadToken(bucketName);
        Response response = uploadManager.put(bais, key, upToken, null, null);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        if(putRet.key == key)
            return true;
        return false;
    }
}
