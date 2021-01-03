package com.yao.eduservice.client;

import com.yao.commonutils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaoheng
 * @date 2021/1/2 15:21
 */
@Service
public class VodClientImpl implements VodClient{
    @Override
    public Result deleteVideos(List<String> ids) {
        return Result.error().message("删除视频失败");
    }

    @Override
    public Result deleteVideo(String id) {
        return Result.error().message("删除视频失败");
    }
}
