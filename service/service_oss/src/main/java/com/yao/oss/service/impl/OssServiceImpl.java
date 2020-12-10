package com.yao.oss.service.impl;

import com.aliyun.oss.OSS;
import com.yao.oss.utils.ConstandPropertiesUtils;
import com.aliyun.oss.OSSClientBuilder;
import com.yao.oss.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author yaoheng
 * @date 2020/12/9 11:04
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadOssAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstandPropertiesUtils.END_POINT;
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //添加随机UUID
            String uuId = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuId + fileName;
            //文件按照上传日期进行fenlei
            //获取当前日期
            String localDate = new DateTime().toString("yyyy-MM-dd");
            fileName = localDate + "/" + fileName;
            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName,fileName , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            return "https://"+bucketName+"."+endpoint+"/"+fileName;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
