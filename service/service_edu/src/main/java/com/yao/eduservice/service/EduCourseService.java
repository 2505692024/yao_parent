package com.yao.eduservice.service;

import com.yao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.eduservice.entity.vo.EduCourseVo;

import java.util.List;

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
    String saveCourse(EduCourseVo eduCourseVo);

    /**
     * 通过课程id查询课程信息
     * @param  courseId
     * @return 课程信息
     */
    EduCourseVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param eduCourseVo
     * @return
     */
    int updateCourse(EduCourseVo eduCourseVo);
}
