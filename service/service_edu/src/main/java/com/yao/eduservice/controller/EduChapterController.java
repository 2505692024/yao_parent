package com.yao.eduservice.controller;


import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduChapter;
import com.yao.eduservice.entity.chapter.ChapterVo;
import com.yao.eduservice.service.EduChapterService;
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
@Api(value = "课程大纲接口", description = "课程大纲接口")
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Resource
    private EduChapterService eduChapterService;

    @ApiOperation(value = "/getCourseOutlineList", notes = "通过id获取课程大纲列表")
    @GetMapping("/getCourseOutlineList/{courseId}")
    public Result getCourseOutlineList(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getCourseOutlineList(courseId);
        return Result.ok().data("data", list);
    }

    @ApiOperation(value = "/addChapter", notes = "添加章节")
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        int i = eduChapterService.addChapter(eduChapter);
        if (i == 0) {
            return Result.error().message("添加失败");
        } else {
            return Result.ok().message("添加成功");
        }
    }

    @ApiOperation(value = "/updateChapter", notes = "修改章节")
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter) {
        int i = eduChapterService.updateChapter(eduChapter);
        if (i == 0) {
            return Result.error().message("修改失败");
        } else {
            return Result.ok().message("修改成功");
        }
    }

    @ApiOperation(value = "/deleteChapter/{chapterId}", notes = "根据id删除章节")
    @DeleteMapping("/deleteChapter/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        int i = eduChapterService.deleteChapterById(chapterId);
        if (i == 0) {
            return Result.error().message("删除失败");
        } else {
            return Result.ok().message("删除成功");
        }
    }

    @ApiOperation(value = "/getChapterById/{chapterId}", notes = "根据id查询章节")
    @GetMapping("/getChapterById/{chapterId}")
    public Result getChapterById(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getChapterById(chapterId);
        return Result.ok().data("data", eduChapter);
    }
}

