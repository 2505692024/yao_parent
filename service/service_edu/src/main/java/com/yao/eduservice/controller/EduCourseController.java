package com.yao.eduservice.controller;


import com.yao.commonutils.Result;
import com.yao.eduservice.entity.vo.EduCourseVo;
import com.yao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
@Api(value = "课程管理接口", description = "课程管理接口")
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {
    @Resource
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    @ResponseBody
    @ApiOperation(notes = "添加课程分类", value = "/addCourseInfo")
    private Result addCourseInfo(@RequestBody EduCourseVo eduCourseVo) {
        eduCourseService.saveCourse(eduCourseVo);
        return Result.ok();
    }
}

