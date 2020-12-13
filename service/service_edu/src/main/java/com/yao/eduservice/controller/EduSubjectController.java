package com.yao.eduservice.controller;


import com.yao.commonutils.Result;
import com.yao.eduservice.entity.subject.OneSubject;
import com.yao.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-12
 */
@Api(value = "课程分类管理")
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Resource
    private EduSubjectService eduSubjectServicel;

    @ApiOperation(value = "添加课程分类")
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file) {
        eduSubjectServicel.saveEduSubject(file, eduSubjectServicel);
        return Result.ok();
    }

    @ApiOperation(value = "获取课程列表")
    @GetMapping("/getSubjectList")
    public Result SubjectTreeList() {
        List<OneSubject> list = eduSubjectServicel.getSubjectTreeList();
        return Result.ok().data("data",list);
    }
}

