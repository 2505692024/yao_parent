package com.yao.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.EduTeacher;
import com.yao.eduservice.service.EduCourseService;
import com.yao.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaoheng
 * @date 2021/1/13 16:43
 */
@Api(value = "前端讲师列表展示", description = "前端讲师列表展示")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherListController {
    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation(value = "/getTeacherList/{current}/{limit}", notes = "前端讲师展示")
    @PostMapping("/getTeacherList/{current}/{limit}")
    public Result getTeacherList(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<EduTeacher>(current, limit);
        IPage<EduTeacher> iPage = eduTeacherService.page(page, null);
        return Result.ok().data("data", iPage.getRecords()).data("total", iPage.getTotal());
    }
    @ApiOperation(value = "/getTeacherInfo/{id}", notes = "讲师详情功能")
    @GetMapping("/getTeacherInfo/{id}")
    public Result lecturerDetails(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        List<EduCourse> courses = eduCourseService.list(new QueryWrapper<EduCourse>().eq("teacher_id", id));
        return Result.ok().data("data",teacher).data("list",courses);
    }

}
