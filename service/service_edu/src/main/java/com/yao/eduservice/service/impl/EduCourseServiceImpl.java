package com.yao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.eduservice.entity.EduChapter;
import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.EduCourseDescription;
import com.yao.eduservice.entity.EduVideo;
import com.yao.eduservice.entity.vo.EduCourseVo;
import com.yao.eduservice.entity.vo.EduPulishVo;
import com.yao.eduservice.mapper.EduChapterMapper;
import com.yao.eduservice.mapper.EduCourseDescriptionMapper;
import com.yao.eduservice.mapper.EduCourseMapper;
import com.yao.eduservice.mapper.EduVideoMapper;
import com.yao.eduservice.service.EduCourseDescriptionService;
import com.yao.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
    @Resource
    private EduChapterMapper eduChapterMapper;
    @Resource
    private EduVideoMapper eduVideoMapper;

    @Override
    public String saveCourse(EduCourseVo eduCourseVo) {
        EduCourse eduCourse = new EduCourse();
        String cid = null;
        BeanUtils.copyProperties(eduCourseVo, eduCourse);
        int insert = eduCourseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new RuntimeException("添加失败");
        } else {
            cid = eduCourse.getId();
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(cid);
            eduCourseDescription.setDescription(eduCourseVo.getDescription());
            int insert1 = eduCourseDescriptionMapper.insert(eduCourseDescription);
            if (insert1 == 0) {
                throw new RuntimeException("添加失败");
            }
        }
        return cid;
    }

    @Override
    public EduCourseVo getCourseInfo(String courseId) {
        //根据id查询课程信息
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(courseId);
        EduCourseVo eduCourseVo = new EduCourseVo();
        BeanUtils.copyProperties(eduCourse, eduCourseVo);
        eduCourseVo.setDescription(eduCourseDescription.getDescription());
        return eduCourseVo;
    }

    @Override
    public int updateCourse(EduCourseVo eduCourseVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseVo, eduCourse);
        int i = eduCourseMapper.updateById(eduCourse);
        if (i != 0) {
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            BeanUtils.copyProperties(eduCourseVo, eduCourseDescription);
            return eduCourseDescriptionMapper.updateById(eduCourseDescription);
        } else {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public EduPulishVo getSubjectAllList(String courseId) {

        return eduCourseMapper.getAllSubjectById(courseId);
    }

    @Override
    public int pulishCourse(String courseId) {
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        eduCourse.setStatus("Normal");
        return eduCourseMapper.updateById(eduCourse);
    }

    @Override
    public int removeCourse(String courseId) {
        //删除对应章节
        eduChapterMapper.delete(new QueryWrapper<EduChapter>().eq("course_id",courseId));
        //删除对应小节
        eduVideoMapper.delete(new QueryWrapper<EduVideo>().eq("course_id",courseId));
        //删除对应简介
        eduCourseDescriptionMapper.delete(new QueryWrapper<EduCourseDescription>().eq("id",courseId));
        //删除本身
        return eduCourseMapper.deleteById(courseId);
    }

}
