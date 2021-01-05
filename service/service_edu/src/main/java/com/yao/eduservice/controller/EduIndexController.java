package com.yao.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.EduTeacher;
import com.yao.eduservice.service.EduCourseService;
import com.yao.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yaoheng
 * @date 2021/1/3 14:49
 */
@Api(value = "名师课程管理", description = "名师课程管理")
@RestController
@RequestMapping("eduservice/index")
@CrossOrigin
public class EduIndexController {
    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "/getTeacherOrCoures", notes = "查询名师和课程")
    @GetMapping("/getTeacherOrCoures")
    public Result getTeacherOrCoures() {
        List<EduCourse> courses = eduCourseService.list(new QueryWrapper<EduCourse>().orderByDesc("id").last("limit 8"));
        List<EduTeacher> teachers = eduTeacherService.list(new QueryWrapper<EduTeacher>().orderByDesc("id").last("limit 4"));
        return Result.ok().data("courseList", courses).data("teacherList", teachers);
    }
}
