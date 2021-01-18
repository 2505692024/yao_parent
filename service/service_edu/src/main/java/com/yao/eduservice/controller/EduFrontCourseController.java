package com.yao.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.front.CourseFrontVo;
import com.yao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yaoheng
 * @date 2021/1/16 9:55
 */
@Api(value = "前端课程列表展示", description = "前端课程列表展示")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduFrontCourseController {
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation(value = "/getCourseQueryList/{current}/{limit}", notes = "课程列表条件查询带分页")
    @PostMapping("/getCourseQueryList/{current}/{limit}")
    public Result getCourseQueryList(@PathVariable Long current, @PathVariable Long limit, @RequestBody CourseFrontVo courseFrontVo) {
        Page<EduCourse> page = new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())){
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        eduCourseService.page(page,wrapper);
        return Result.ok().data("list",page.getRecords()).data("total",page.getTotal());
    }
}
