package com.yao.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.yao.commonutils.Result;
import com.yao.eduservice.entity.EduSubject;
import com.yao.eduservice.entity.excel.ExcelSubject;
import com.yao.eduservice.entity.subject.OneSubject;
import com.yao.eduservice.entity.subject.TwoSubject;
import com.yao.eduservice.listener.ExcelSubjectListener;
import com.yao.eduservice.mapper.EduSubjectMapper;
import com.yao.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yaoheng
 * @since 2020-12-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Resource
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void saveEduSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //得到文件的输入流
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubject.class, new ExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getSubjectTreeList() {
        List<OneSubject> oneSubjects = new ArrayList<>();
        //获取所有一级分类
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(new QueryWrapper<EduSubject>().eq("parent_id",0));
        //查询所有二级分类
        List<EduSubject> eduSubjectList = eduSubjectMapper.selectList(new QueryWrapper<EduSubject>().ne("parent_id",0));
        //封装一级分类
        for (int i = 0; i < eduSubjects.size(); i ++){
            EduSubject e = eduSubjects.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(e,oneSubject);
            oneSubjects.add(oneSubject);
            List<TwoSubject> twoSubjects1 = new ArrayList<>();
            for (int j = 0; j < eduSubjectList.size(); j++) {
                TwoSubject twoSubject = new TwoSubject();
                EduSubject eduSubject = eduSubjectList.get(j);
                if (eduSubject.getParentId().equals(e.getId())){
                    BeanUtils.copyProperties(eduSubject,twoSubject);
                    twoSubjects1.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjects1);
        }
        return oneSubjects;
    }
}
