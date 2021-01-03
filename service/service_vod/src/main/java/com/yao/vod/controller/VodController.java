package com.yao.vod.controller;

import com.yao.commonutils.Result;
import com.yao.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yaoheng
 * @date 2020/12/30 8:03
 */
@Api(value = "视频调拨控制器", description = "视频点播控制器")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Resource
    private VodService eduVodService;

    @ApiOperation(value = "/uploadVideo", notes = "视频上传接口")
    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file) {
        String videoId = eduVodService.uploadVideo(file);
        return Result.ok().data("videoId", videoId);
    }
    @ApiOperation(value = "/deleteVideo", notes = "删除视频")
    @DeleteMapping("/deleteVideo/{id}")
    public Result deleteVideo(@PathVariable String id) {
        eduVodService.deleteVideo(id);
        return Result.ok().message("删除成功");
    }

    @ApiOperation(value = "/deleteVideo", notes = "删除视频")
    @DeleteMapping("/deleteVideos")
    public Result deleteVideos(@RequestParam List<String> ids) {
        eduVodService.deleteVideos(ids);
        return Result.ok().message("删除成功");
    }
}
