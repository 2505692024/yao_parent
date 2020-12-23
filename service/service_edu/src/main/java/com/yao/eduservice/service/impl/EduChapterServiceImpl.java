package com.yao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.eduservice.entity.EduChapter;
import com.yao.eduservice.entity.EduVideo;
import com.yao.eduservice.entity.chapter.ChapterVo;
import com.yao.eduservice.entity.chapter.VideoVo;
import com.yao.eduservice.mapper.EduChapterMapper;
import com.yao.eduservice.mapper.EduVideoMapper;
import com.yao.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Resource
    private EduChapterMapper eduChapterMapper;
    @Resource
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVo> getCourseOutlineList(String courseId) {
        List<ChapterVo> chapterVos = new ArrayList<>();
        //查询所有课程章节
        List<EduChapter> eduChapters = eduChapterMapper.selectList(new QueryWrapper<EduChapter>().eq("course_id", courseId));
        //查询所有课时
        List<EduVideo> eduVideos = eduVideoMapper.selectList(null);
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            chapterVos.add(chapterVo);
            List<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo = eduVideos.get(j);
                VideoVo videoVo = new VideoVo();
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }
        return chapterVos;
    }

    @Override
    public int addChapter(EduChapter eduChapter) {

        return eduChapterMapper.insert(eduChapter);
    }

    @Override
    public int updateChapter(EduChapter eduChapter) {

        return eduChapterMapper.updateById(eduChapter);
    }

    @Override
    public int deleteChapterById(String chapterId) {
        //判断章节下是否有小节，有则不删
        List<EduVideo> eduVideos = eduVideoMapper.selectList(new QueryWrapper<EduVideo>().eq("chapter_id", chapterId));
        if (eduVideos.isEmpty()) {
            return eduChapterMapper.deleteById(chapterId);
        } else {
            throw new RuntimeException("删除失败，请检查！");
        }
    }

    @Override
    public EduChapter getChapterById(String chapterId) {

        return eduChapterMapper.selectById(chapterId);
    }
}
