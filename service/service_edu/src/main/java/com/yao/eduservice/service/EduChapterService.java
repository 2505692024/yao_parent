package com.yao.eduservice.service;

import com.yao.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 获取课程大纲列表
     *
     * @param String courseId
     * @return list
     */
    List<ChapterVo> getCourseOutlineList(String courseId);

    /**
     * 添加章节
     *
     * @param eduChapter
     * @return i
     */
    int addChapter(EduChapter eduChapter);

    /**
     * 修改章节信息
     *
     * @param eduChapter
     * @return
     */
    int updateChapter(EduChapter eduChapter);

    /**
     * 根据id删除章节
     *
     * @param chapterId
     * @return
     */
    int deleteChapterById(String chapterId);

    /**
     * 根据id查询章节信息
     *
     * @param chapterId
     * @return
     */
    EduChapter getChapterById(String chapterId);
}
