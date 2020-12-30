package com.yao.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.vo.EduCourseVo;
import com.yao.eduservice.entity.vo.EduPulishVo;
import com.yao.eduservice.entity.vo.EduTeacherVo;
import com.yao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
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

    @ApiOperation(notes = "获取课程信息", value = "/getSubjectListById/{courseId}")
    @GetMapping("/getSubjectListById/{courseId}")
    public Result getSubjectListById(@PathVariable String courseId) {
        EduPulishVo eduPulishVo = eduCourseService.getSubjectAllList(courseId);
        return Result.ok().data("data", eduPulishVo);
    }

    @ApiOperation(notes = "课程信息发布", value = "/pulishCourse/{courseId}")
    @PostMapping("/pulishCourse/{courseId}")
    public Result pulishCourse(@PathVariable String courseId) {
        int i = eduCourseService.pulishCourse(courseId);
        if (i == 0) {
            return Result.error().message("发布失败");
        } else {
            return Result.ok().message("发布成功");
        }
    }

    @ApiOperation(value = "/getQueryCourse/{current}/{limit}", notes = "多条件组合分页查询")
    @PostMapping("/getQueryCourse/{current}/{limit}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", paramType = "path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "limit", value = "每页记录数", paramType = "path", required = true, dataType = "Long"),
    })
    public Result getQueryCourse(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) EduCourseVo eduCourseVo) {
        //创建分页对象
        Page<EduCourse> teacherPage = new Page<>(current, limit);
        //创建构造条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<EduCourse>();
        //判断条件是否为空，不为空拼接条件
        if (!StringUtils.isEmpty(eduCourseVo.getTitle())) {
            wrapper.like("title", eduCourseVo.getTitle());
        }
        if (!StringUtils.isEmpty(eduCourseVo.getStatus())) {
            wrapper.eq("status", eduCourseVo.getStatus());
        }
        wrapper.orderByDesc("gmt_create");
        eduCourseService.page(teacherPage, wrapper);
        Long total = teacherPage.getTotal();
        List<EduCourse> records = teacherPage.getRecords();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "/deleteCourse/{courseId}", notes = "根据id删除课程")
    @DeleteMapping("/deleteCourse/{courseId}")
    public Result deleteCourse(@PathVariable String courseId) {
        int i = eduCourseService.removeCourse(courseId);
        if (i == 0) {
            return Result.error().message("删除失败");
        } else {
            return Result.ok().message("删除成功");
        }
    }
}