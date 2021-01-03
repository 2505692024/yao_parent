package com.yao.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.commonutils.Result;
import com.yao.eduservice.client.VodClient;
import com.yao.eduservice.entity.EduVideo;
import com.yao.eduservice.mapper.EduVideoMapper;
import com.yao.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
@Api(value = "课程小节管理", description = "课程小节管理")
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
    @Resource
    private EduVideoService eduVideoService;
    @Resource
    private EduVideoMapper eduVideoMapper;
    @Resource
    private VodClient vodClient;

    @ApiOperation(value = "/addVideo", notes = "添加小节")
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        boolean save = eduVideoService.save(eduVideo);
        if (save) {
            return Result.ok().message("添加成功");
        } else {
            return Result.error().message("添加失败");
        }
    }

    @ApiOperation(value = "/deleteVideo", notes = "根据id删除小节")
    @DeleteMapping("/deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable String videoId) {
        EduVideo byId = eduVideoService.getById(videoId);
        Result result = vodClient.deleteVideo(byId.getVideoSourceId());
        if (result.getCode() == 20001){
            throw new RuntimeException("删除失败被熔断");
        }
        boolean b = eduVideoService.removeById(videoId);
        if (b) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }

    @ApiOperation(value = "/updateVideo", notes = "修改小节")
    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo) {
        boolean b = eduVideoService.updateById(eduVideo);
        if (b) {
            return Result.ok().message("修改成功");
        } else {
            return Result.error().message("修改失败");
        }
    }

    @ApiOperation(value = "/getVideoById", notes = "根据id查询小节")
    @GetMapping("/getVideoById/{videoId}")
    public Result getVideoById(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return Result.ok().data("data", eduVideo);
    }
}

