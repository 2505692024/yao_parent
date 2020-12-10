package com.yao.oss.utils;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yaoheng
 * @date 2020/12/9 10:43
 */
@Component
public class ConstandPropertiesUtils implements InitializingBean {
    @ApiModelProperty(value = "节点地址")
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @ApiModelProperty(value = "实例id")
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @ApiModelProperty(value = "实例key")
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @ApiModelProperty(value = "实例名称")
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        END_POINT = endpoint;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
