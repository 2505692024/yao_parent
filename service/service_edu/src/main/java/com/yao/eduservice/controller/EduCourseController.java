package com.yao.eduservice.controller;


import com.yao.commonutils.Result;
import com.yao.eduservice.entity.vo.EduCourseVo;
import com.yao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        //返回成功后的id
        String cid = eduCourseService.saveCourse(eduCourseVo);
        return Result.ok().data("courseId", cid);
    }

    @ApiOperation(notes = "根据id查询课程信息", value = "/getCourseInfo/{courseId}")
    @GetMapping("/getCourseInfo/{courseId}")
    @ResponseBody
    public Result getCourseInfo(@PathVariable String courseId) {
        EduCourseVo eduCourseVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("data", eduCourseVo);
    }

    @ApiOperation(notes = "修改课程信息", value = "/getCourseInfo/{courseId}")
    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody EduCourseVo eduCourseVo) {
        int i = eduCourseService.updateCourse(eduCourseVo);
        if (i == 0) {
            return Result.error().message("修改失败");
        } else {
            return Result.ok().message("修改成功");
        }
    }
}

