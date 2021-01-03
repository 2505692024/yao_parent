package com.yao.eduservice.client;

import com.yao.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yaoheng
 * @date 2021/1/2 8:38
 */
@FeignClient(name = "service-vod" , fallback = VodClientImpl.class)
@Component
public interface VodClient {
    /**
     * 远程调用service_vod模块中的删除阿里云上传视频功能
     * @param ids
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteVideos")
    public Result deleteVideos(@RequestParam List<String> ids);

    /**
     * 单个删除
     * @param id
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteVideo")
    public Result deleteVideo(@PathVariable String id);
}
