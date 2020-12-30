package com.yao.eduservice.mapper;

import com.yao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yao.eduservice.entity.vo.EduPulishVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 根据id查询课程的所有信息
     *
     * @param courseId
     * @return
     */
    public EduPulishVo getAllSubjectById(@Param("courseId") String courseId);
}
