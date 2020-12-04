package com.yao.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduTeacher;
import com.yao.eduservice.entity.vo.EduTeacherVo;
import com.yao.eduservice.service.EduTeacherService;
import com.yao.servicebase.exceptionhandler.EduException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-03
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/edu_teacher")
public class EduTeacherController {
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping("/findAll")
    public Result getAllTeacher() {

        return Result.ok().data("data", eduTeacherService.list(null));
    }

    @ApiOperation(value = "根据id逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result deleteTeacher(@ApiParam(value = "讲师Id", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/getPageTeacher/{current}/{limit}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", paramType = "path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "limit", value = "每页记录数", paramType = "path", required = true, dataType = "Long"),
    })
    public Result getPageTeacher(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, null);
        //总记录数
        Long total = page.getTotal();
        //数据list集合
        List<EduTeacher> records = page.getRecords();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "多条件组合分页查询")
    @PostMapping("/getQueryTeacher/{current}/{limit}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", paramType = "path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "limit", value = "每页记录数", paramType = "path", required = true, dataType = "Long"),
    })

    public Result getQueryTeacher(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) EduTeacherVo eduTeacherVo) {
        //创建分页对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        //创建构造条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<EduTeacher>();
        //判断条件是否为空，不为空拼接条件
        if (!StringUtils.isEmpty(eduTeacherVo.getName())) {
            wrapper.like("name", eduTeacherVo.getName());
        }
        if (!StringUtils.isEmpty(eduTeacherVo.getLevel())) {
            wrapper.eq("level", eduTeacherVo.getLevel());
        }
        if (!StringUtils.isEmpty(eduTeacherVo.getBegin())) {
            //大于等于创建时间
            wrapper.ge("gmt_create", eduTeacherVo.getBegin());
        }
        if (!StringUtils.isEmpty(eduTeacherVo.getEnd())) {
            //小于等于创建时间
            wrapper.le("gmt_create", eduTeacherVo.getEnd());
        }
        eduTeacherService.page(teacherPage, wrapper);
        Long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@ApiParam(value = "讲师id", required = true) @PathVariable String id) {

        return Result.ok().data("data", eduTeacherService.getById(id));
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateTeahcer")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

