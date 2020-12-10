package com.yao.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yaoheng
 * @date 2020/12/9 11:04
 */
public interface OssService {
    /**
     * 上传文件
     * @param
     * @return 图片url
     */
    String uploadOssAvatar(MultipartFile file);
}
