package com.yao.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yaoheng
 * @date 2020/12/30 8:23
 */
public interface VodService {
    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    void deleteVideo(String id);
}
