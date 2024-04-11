package com.pray.utils;

import com.pray.common.QiniuConfigProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


/**
 * QiniuUploadUtil
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/1 21:17
 */
@Component
public class QiNiuUploadUtil {
    @Resource
    private QiniuConfigProperties qiniuConfigProperties;
    public String uploadByBytes(byte[] bytes) throws QiniuException {
        //华南地区选择区域
        Configuration cfg=new Configuration(Region.region2());
        //根据密钥来创建Auth验证
        Auth auth=Auth.create(
                qiniuConfigProperties.getAccessKey(),
                qiniuConfigProperties.getSecretKey());
        BucketManager bucketManager=new BucketManager(auth,cfg);
        UploadManager uploadManager=new UploadManager(cfg);//上传处理器

        //回调获取验证token
        String token = auth.uploadToken(qiniuConfigProperties.getBucketName());
        String name= String.valueOf(System.nanoTime());
        Response response = uploadManager.put(bytes,name,token);
        return response.toString();
    }
}
