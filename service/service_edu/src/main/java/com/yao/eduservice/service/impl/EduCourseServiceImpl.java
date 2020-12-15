package com.yao.eduservice.service.impl;

import com.yao.eduservice.entity.EduCourse;
import com.yao.eduservice.entity.EduCourseDescription;
import com.yao.eduservice.entity.vo.EduCourseVo;
import com.yao.eduservice.mapper.EduCourseDescriptionMapper;
import com.yao.eduservice.mapper.EduCourseMapper;
import com.yao.eduservice.service.EduCourseDescriptionService;
import com.yao.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public void saveCourse(EduCourseVo eduCourseVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseVo, eduCourse);
        int insert = eduCourseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new RuntimeException("添加失败");
        } else {
            String cid = eduCourse.getId();
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(cid);
            eduCourseDescription.setDescription(eduCourseVo.getDescription());
            int insert1 = eduCourseDescriptionMapper.insert(eduCourseDescription);
            if (insert1 == 0) {
                throw new RuntimeException("添加失败");
            }
        }

    }
}
