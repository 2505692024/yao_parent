package com.yao.eduservice.service;

import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-12
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     * @param file
     * @return json
     */
    void saveEduSubject(MultipartFile file,EduSubjectService eduSubjectService);

    /**
     * 获取课程分类列表
     * @return
     */
    List<OneSubject> getSubjectTreeList();
}
