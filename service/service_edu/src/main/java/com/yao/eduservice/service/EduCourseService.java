package com.yao.eduservice.service;

import com.yao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.eduservice.entity.vo.EduCourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 添加课程信息
     * @param  eduCourseVo
     */
    void saveCourse(EduCourseVo eduCourseVo);
}
