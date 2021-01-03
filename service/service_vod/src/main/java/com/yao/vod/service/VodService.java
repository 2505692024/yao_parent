package com.yao.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @param ids
     * @return
     */
    void deleteVideos(List<String> ids);
    /**
     * 删除视频
     * @param ids
     * @return
     */
    void deleteVideo(String id);
}
